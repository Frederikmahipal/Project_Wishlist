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
                request.name(),
                LocalDateTime.now()
        );
        return wishlistRepository.createWishlistForUser(wishlist, user);
    }

    public boolean deleteWishlist(User user, WishlistDeletionRequest request) {
        Wishlist wishlist = new Wishlist(
                request.name(),
                request.id(),
                null
        );
        return wishlistRepository.deleteWishlistForUser(wishlist, user);
    }
}
