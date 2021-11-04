package gruppe8.project_wishlist.wishlists;

public class WishlistCreationRequest {
    private final String name;

    public WishlistCreationRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "WishlistCreationRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
