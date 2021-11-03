package gruppe8.project_wishlist.repositories;


import gruppe8.project_wishlist.services.DatabaseService;
import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.models.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishlistRepository {
    private final DatabaseService databaseService;

    @Autowired
    public WishlistRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
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
            preparedStatement.setInt(1,2);
            preparedStatement.setString(2,wishlist.getName());
            preparedStatement.setDate(3,wishlist.getCreated());

            preparedStatement.execute();

        }catch (SQLException | NullPointerException e){
            System.out.println(e.getMessage());
        }

    }


    public List<Wishlist> getWishlistsFromUser(User user) {
        return null;
    }

    public boolean createWishlistForUser(User user) {
        return false;
    }
}
