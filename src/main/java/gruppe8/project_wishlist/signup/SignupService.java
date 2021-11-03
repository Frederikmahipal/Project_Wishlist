package gruppe8.project_wishlist.signup;

import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.repositories.UserRepository;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    private final UserRepository userRepository;
    private final Argon2PasswordEncoder passwordEncoder;

    public SignupService(UserRepository userRepository, Argon2PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Boolean register(SignupRequest request) {
        User user = new User(
                request.getFullName(),
                request.getEmail(),
                passwordEncoder.encode( request.getPassword() )
        );
        return userRepository.create(user);
    }
}
