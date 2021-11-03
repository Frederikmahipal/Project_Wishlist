package gruppe8.project_wishlist.repositories;

import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.services.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository implements UserDetailsService {
    private final DatabaseService databaseService;
    private final Logger logger;

    public UserRepository(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!userExists(username)) throw new UsernameNotFoundException(username);

        String query =  """
                        SELECT id, email, fullName, argon2Password, created
                        FROM users
                        WHERE email LIKE ?
                        """;
        User user = null;

        try (Connection connection = databaseService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())  {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("fullName"),
                        resultSet.getString("email"),
                        resultSet.getString("argon2Password")
                );
            }
        } catch (SQLException e){
            logger.error(e.getMessage());
        }
        return user;
    }

    public boolean userExists(String username) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT EXISTS(SELECT email FROM users WHERE email LIKE ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return resultSet.getInt(1) == 1;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean create(User user) {
        if ( userExists(user.getEmail()) ) { return false; }

        try (Connection connection = databaseService.getConnection()) {
            String query = "INSERT INTO users (email, fullName, argon2Password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFullName());
            preparedStatement.setString(3, user.getArgon2Password());
            preparedStatement.execute();
            return preparedStatement.getUpdateCount() == 1;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    // TODO
    public boolean update(User user) {
        return false;
    }
    public boolean delete(User user) {
        return false;
    }
}