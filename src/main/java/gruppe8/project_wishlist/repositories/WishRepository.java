package gruppe8.project_wishlist.repositories;

import gruppe8.project_wishlist.services.DatabaseService;
import gruppe8.project_wishlist.models.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishRepository {
    private final DatabaseService databaseService;

    @Autowired
    public WishRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
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

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return wishList;
    }
}
