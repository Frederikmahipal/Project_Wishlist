package gruppe8.project_wishlist.repository;


import gruppe8.project_wishlist.databaseManagement.DatabaseConnection;
import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.models.Wish;
import gruppe8.project_wishlist.models.Wishlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishListRepository {

    static ArrayList<Wishlist> listOfWishLists = new ArrayList<>();
    UserRepository userRepository = new UserRepository();
    Connection connection = DatabaseConnection.getConnection();

    public List<Wishlist> getAllWishLists(){
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
    }


    public void addWishListToDatabase(Wishlist wishlist, User user){

        try{
            // insert into wishlists (userId, name, created) VALUES  (?,?,?)
            PreparedStatement preparedStatement = connection.prepareStatement("insert into wishlist.wishlists (userId, name, created) VALUES  (?,?,?)");
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setString(2,wishlist.getName());
            preparedStatement.setDate(3,wishlist.getCreated());

            preparedStatement.execute();

        }catch (SQLException | NullPointerException e){
            System.out.println(e.getMessage());
        }

    }


}
