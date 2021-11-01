package gruppe8.project_wishlist.models;

public class Wishlist {
    private String title;
    private int id;
    private Wish wish;

    public Wishlist(String title, Wish wish) {
        this.title = title;
        this.wish = wish;
    }

    public Wishlist(int id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Wish getWish() {
        return wish;
    }

    public void setWish(Wish wish) {
        this.wish = wish;
    }
}
