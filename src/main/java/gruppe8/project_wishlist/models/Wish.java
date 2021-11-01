package gruppe8.project_wishlist.models;

public class Wish {
    private int id;
    private float price;
    private String title, url, note;

    public Wish(float price, String title, String url, String note) {
        this.price = price;
        this.title = title;
        this.url = url;
        this.note = note;
    }

    public Wish(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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
}
