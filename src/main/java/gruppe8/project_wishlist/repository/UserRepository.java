package gruppe8.project_wishlist.repository;

import gruppe8.project_wishlist.databaseManagement.DatabaseConnection;
import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.models.Wishlist;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {


    static ArrayList<User> userList = new ArrayList<>();
    Connection connection = DatabaseConnection.getConnection();

    public List<User> getAllUsers(){
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM wishlist.users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())  {
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );

                userList.add(user);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public void addUserToDatabase(User user){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("insert into wishlist.users (email, fullName, argon2Password) VALUES (?,?,?)");
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getFullName());
            preparedStatement.setString(3,user.getArgon2Password());

            preparedStatement.execute();


        }catch (SQLException | NullPointerException e){
            System.out.println(e.getMessage());
        }

    }

}