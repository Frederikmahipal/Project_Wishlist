package gruppe8.project_wishlist.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    private Long id;
    private String fullName;
    private String email;
    private String argon2Password;
    private LocalDateTime created;

    public User(Long id, String name, String email, String password, LocalDateTime created) {
        this.id = id;
        this.fullName = name;
        this.email = email;
        this.argon2Password = password;
        this.created = created;
    }
    public User(String name, String email, String password) {
        this.fullName = name;
        this.email = email;
        this.argon2Password = password;
    }

    // Part of UserDetails interface
    @Override
    public String getUsername() { return this.email; }
    @Override
    public String getPassword() { return this.argon2Password; }

    // The following methods are required to implement UserDetails interface.
    // We don't make use of GrantedAuthority/Roles, or locks, or expiration.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority("ROLE_USER") );
    }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

    // Getters and Setters for User.
    // TODO: Maybe setters should be handled through the repository??
    public Long getId() {
        return id;
    }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getArgon2Password() { return argon2Password; }
    public LocalDateTime getCreated() { return created; }
    public void setId(Long id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setArgon2Password(String argon2Password) { this.argon2Password = argon2Password; }
    public void setCreated(LocalDateTime created) { this.created = created; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", argon2Password='" + argon2Password + '\'' +
                ", created=" + created.toString() +
                '}';
    }
}
