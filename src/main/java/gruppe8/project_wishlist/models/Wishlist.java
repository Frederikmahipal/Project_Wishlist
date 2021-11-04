package gruppe8.project_wishlist.models;

import java.time.LocalDateTime;

public class Wishlist {
    private String name;
    private Long id;
    private LocalDateTime created;

    public Wishlist(String name, Long id, LocalDateTime created) {
        this.name = name;
        this.id = id;
        this.created = created;
    }
    public Wishlist(String name, LocalDateTime created) {
        this.name = name;
        this.created = created;
    }

    public String getName() { return name; }
    public Long getId() { return id; }
    public LocalDateTime getCreated() { return created; }

    public void setName(String name) { this.name = name; }
    public void setId(Long id) { this.id = id; }
    public void setCreated(LocalDateTime created) { this.created = created; }
}