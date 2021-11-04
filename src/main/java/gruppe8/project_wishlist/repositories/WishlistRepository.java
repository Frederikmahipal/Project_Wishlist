package gruppe8.project_wishlist.repositories;


import gruppe8.project_wishlist.services.DatabaseService;
import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.models.Wishlist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishlistRepository {
    private final DatabaseService databaseService;
    private final Logger logger;

    @Autowired
    public WishlistRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    /*public List<Wishlist> getAllWishLists() {
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM wishlist.wishlists");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())  {
                Wishlist wishlist = new Wishlist(
                        resultSet.getString("name"),
                        resultSet.getInt("id"),
                        resultSet.getDate("created")
                );

                listOfWishLists.add(wishlist);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listOfWishLists;
    }*/


    public void addWishListToDatabase(Wishlist wishlist, User user){
        try{
            // insert into wishlists (userId, name, created) VALUES  (?,?,?)
            PreparedStatement preparedStatement = databaseService.getConnection().prepareStatement("insert into wishlist.wishlists (userId, name, created) VALUES  (?,?,?)");
            preparedStatement.setLong(1,wishlist.getId());
            preparedStatement.setString(2,wishlist.getName());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(wishlist.getCreated()));

            preparedStatement.execute();

        }catch (SQLException | NullPointerException e){
            System.out.println(e.getMessage());
        }

    }


    public List<Wishlist> getFromUser(User user) {
        List<Wishlist> wishlists = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT name, id, created FROM wishlists WHERE userId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Timestamp timestamp = resultSet.getTimestamp(3);
                LocalDateTime localDateTime = timestamp.toLocalDateTime();

                wishlists.add( new Wishlist(
                        resultSet.getString(1),
                        resultSet.getLong(2),
                        localDateTime
                ));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return wishlists;
    }

    public boolean createWishlistForUser(Wishlist wishlist, User user) {
        try (Connection connection = databaseService.getConnection()){
            String query = "INSERT INTO wishlists (name, created, userId) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,wishlist.getName());
            preparedStatement.setTimestamp( 2, Timestamp.valueOf(wishlist.getCreated()) );
            preparedStatement.setLong(3, user.getId());

            Integer changedRows = preparedStatement.executeUpdate();
            if (changedRows == 1) { return true; }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}
