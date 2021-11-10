package gruppe8.project_wishlist.models;

import org.springframework.core.io.Resource;

import java.sql.Blob;
import java.time.LocalDateTime;

public class Wish {
    private Long id;
    private Double price;
    private String title, url, note;
    private Resource image;
    private LocalDateTime created;

    public Wish() {}

    public Wish(double price, String title, String url, String note, Resource image) {
        this.price = price;
        this.title = title;
        this.url = url;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Resource getImage() {
        return image;
    }

    public void setImage(Resource image) {
        this.image = image;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Wish{" +
                "wishlistId=" + id +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
