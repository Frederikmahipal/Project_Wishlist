package gruppe8.project_wishlist.wishlists;

import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.models.Wishlist;
import gruppe8.project_wishlist.repositories.WishlistRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public boolean createWishlist(User user, WishlistCreationRequest request) {
        Wishlist wishlist = new Wishlist(
                request.getName(),
                LocalDateTime.now()
        );

        return wishlistRepository.createWishlistForUser(wishlist, user);
    }
}
