package gruppe8.project_wishlist.repository;


import gruppe8.project_wishlist.databaseManagement.DatabaseConnection;
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
    Connection connection = DatabaseConnection.getConnection();

    public List<Wishlist> getAllWishLists(){
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM wishlists");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())  {
                Wishlist wishlist = new Wishlist(
                        resultSet.getString("name"),
                        resultSet.getInt("id")
                );

                listOfWishLists.add(wishlist);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listOfWishLists;
    }

}
