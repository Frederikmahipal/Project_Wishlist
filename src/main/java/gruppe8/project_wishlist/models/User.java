package gruppe8.project_wishlist.models;

import java.sql.Date;

public class User {
    private int id;
    private String fullName, email, argon2Password;


    public User(String name, String email, String password) {
        this.fullName = name;
        this.email = email;
        this.argon2Password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArgon2Password() {
        return argon2Password;
    }

    public void setArgon2Password(String argon2Password) {
        this.argon2Password = argon2Password;
    }

}
