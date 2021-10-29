package gruppe8.project_wishlist;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class ArgonTest {
    // Small proof-of-concept for using Argon2 in Java.
    // https://github.com/phxql/argon2-jvm

    public static void main(String[] args) {
        // Select the argon2id type
        Argon2Factory.Argon2Types type = Argon2Factory.Argon2Types.ARGON2id;
        // Create Argon2 instance with type
        Argon2 argon2 = Argon2Factory.create(type);

        // Create Scanner for reading user input
        Scanner scanner = new Scanner(System.in);
        Charset charset = StandardCharsets.UTF_8;

        // Array for storing user input
        byte[] input;

        // String for holding hashed password
        String hashed_password;

        // Read password from user
        // (maybe it's not necessary to specify UTF-8 encoding?)
        System.out.print("Please enter a password: ");
        input = scanner.nextLine().getBytes(charset);

        // Hash the password
        // The password is hash twice *only* to demonstrate that the hash changes each time because of the random salt.
        try {
            hashed_password = argon2.hash(10, 65546, 1, input);
            System.out.println("hashed_password = " + hashed_password);
            hashed_password = argon2.hash(10, 65546, 1, input);
            System.out.println("hashed_password = " + hashed_password);
        } finally {
            argon2.wipeArray(input);
        }

        // Confirm password from user
        // (maybe it's not necessary to specify UTF-8 encoding?)
        System.out.print("Please confirm password: ");
        input = scanner.nextLine().getBytes(charset);

        // To compare a password to a hashed password we use Argon2.verify()
        // hashed password contains all information required for argon2 to work, i.e. version, configuration, and random salt
        try {
            boolean doesMatch = argon2.verify(hashed_password, input);
            System.out.println(
                    "The two passwords " +
                    (doesMatch ? "does match." : "does not match.")
            );
        } finally {
            argon2.wipeArray(input);
        }

    }
}
