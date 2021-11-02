package gruppe8.project_wishlist.repository;

import gruppe8.project_wishlist.databaseManagement.DatabaseConnection;
import gruppe8.project_wishlist.models.Wish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishRepository {

    static ArrayList<Wish> wishList = new ArrayList<>();
    Connection connection = DatabaseConnection.getConnection();

    public List<Wish> getAllWishes(){
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM wishes");
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

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return wishList;
    }
}
