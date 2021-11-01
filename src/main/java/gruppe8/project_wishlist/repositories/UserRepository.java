package gruppe8.project_wishlist.repositories;

import gruppe8.project_wishlist.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private UserRepository() {
    }

    public User getUserByEmail(String email) {
        if (email.equals("simon.gredal@icloud.com")) {
            return new User(
                    1,
                    "Simon Gredal",
                    email,
                    "$argon2id$v=19$m=65546,t=10,p=1$4+LJ3HF7sLBx3rN0fAWYfA$80uIctb6p5b14H61LuU++l/Ds1mJ7PvWxJ8BWp47jCQ"
            );
        }
        return null;
    }
}
