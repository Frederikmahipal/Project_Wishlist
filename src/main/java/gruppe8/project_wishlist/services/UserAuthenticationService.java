package gruppe8.project_wishlist.services;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Service
public class UserAuthenticationService {

    private final UserRepository userRepository;
    private final Argon2 argon2;

    @Autowired
    private UserAuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.argon2 = Argon2Factory.create( Argon2Factory.Argon2Types.ARGON2id );
    }

    public User authenticateUser(String email, String password) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) { return null; };

        boolean authenticationSucces = argon2.verify(
                user.getArgon2Password(),
                password.getBytes(StandardCharsets.UTF_8)
        );

        if (!authenticationSucces) { return null; }

        return user;
    }
}
