package gruppe8.project_wishlist.repositories;

import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.models.Wishlist;
import gruppe8.project_wishlist.services.DatabaseService;
import gruppe8.project_wishlist.models.Wish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishRepository {
    private final DatabaseService databaseService;
    private final Logger logger;

    @Autowired
    public WishRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    public List<Wish> getAllWishes(){
        List<Wish> wishList = new ArrayList<>();
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = databaseService.getConnection().prepareStatement("SELECT * FROM wishes");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())  {
                Wish wish = new Wish(
                        resultSet.getFloat("price"),
                        resultSet.getString("title"),
                        resultSet.getString("url"),
                        resultSet.getString("note")
                );

                wishList.add(wish);
            }

        } catch (SQLException e){
            logger.error(e.getMessage());
        }
        return wishList;
    }
    public List<Wish> getAllWishesFromWishlist(Wishlist wishlist){
        List<Wish> wishList = new ArrayList<>();
        PreparedStatement preparedStatement = null;

        try{
            String query = "SELECT * FROM wishlist.wishes WHERE wishlistId=?;";
            preparedStatement = databaseService.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, wishlist.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())  {
                Wish wish = new Wish(
                        resultSet.getFloat("price"),
                        resultSet.getString("title"),
                        resultSet.getString("url"),
                        resultSet.getString("note")
                );

                wishList.add(wish);
            }

        } catch (SQLException e){
            logger.error(e.getMessage());
        }
        return wishList;
    }
    public boolean createWishForWishlist(Wishlist wishlist, Wish wish) {
        try (Connection connection = databaseService.getConnection()){
            String query = "INSERT INTO wishes (wishlistId, created, title, price, url, note) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,wishlist.getId());
            preparedStatement.setTimestamp( 2, Timestamp.valueOf(wishlist.getCreated()) );
            preparedStatement.setString(3, wish.getTitle());
            preparedStatement.setFloat(4, wish.getPrice());
            preparedStatement.setString(5, wish.getUrl());
            preparedStatement.setString(6, wish.getNote());

            int changedRows = preparedStatement.executeUpdate();
            if (changedRows == 1) { return true; }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteWishFromWishlist(Wish wish) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "DELETE FROM wishlists WHERE (id = ?);";
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
