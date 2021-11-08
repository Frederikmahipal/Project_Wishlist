package gruppe8.project_wishlist.repositories;

import gruppe8.project_wishlist.models.Wishlist;
import gruppe8.project_wishlist.services.DatabaseService;
import gruppe8.project_wishlist.models.Wish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishRepository {
    private final DatabaseService databaseService;
    private final Logger logger;

    public WishRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    public boolean createWish(Wishlist wishlist, Wish wish) {
        //https://stackoverflow.com/questions/1915166/how-to-get-the-insert-id-in-jdbc#1915197
        try (Connection connection = databaseService.getConnection()) {
            String query =  "INSERT INTO wishes (wishlistid, created, title, price, url, note)" +
                            "VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, wishlist.getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf( wish.getCreated() ));
            preparedStatement.setString(3, wish.getTitle());
            preparedStatement.setDouble(4, wish.getPrice());
            preparedStatement.setString(5, wish.getUrl());
            preparedStatement.setString(6, wish.getNote());

            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows != 1) {
                logger.error("Failed to INSERT wish");
                return false;
            }
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public List<Wish> getAllWishesFromWishlist(Wishlist wishlist){
        List<Wish> wishList = new ArrayList<>();
        PreparedStatement preparedStatement = null;

        try {
            String query = "SELECT price, title, url, note, id, created FROM wishes WHERE wishlistId = ?;";
            preparedStatement = databaseService.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, wishlist.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())  {
                Wish wish = new Wish();
                wish.setPrice(resultSet.getDouble("price"));
                wish.setTitle(resultSet.getString("title"));
                wish.setUrl(resultSet.getString("url"));
                wish.setNote(resultSet.getString("note"));
                wish.setId(resultSet.getLong("id"));
                wish.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                wishList.add(wish);
            }

        } catch (SQLException e){
            logger.error(e.getMessage());
        }
        return wishList;
    }

    public boolean deleteWishFromWishlist(Wish wish) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "DELETE FROM wishlist.wishes WHERE (id = ?);";
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, wish.getId());
            int changedRows = preparedStatement.executeUpdate();
            if (changedRows == 1) {
                connection.commit();
                return true;
            }
            connection.rollback();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

}
