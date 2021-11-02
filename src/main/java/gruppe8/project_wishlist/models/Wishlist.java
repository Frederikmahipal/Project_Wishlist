package gruppe8.project_wishlist.models;

import java.sql.Date;

public class Wishlist {
    private String name;
    private int id;
    private Date created;


    public Wishlist(String name, int id, Date created) {
        this.name = name;
        this.id = id;
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}