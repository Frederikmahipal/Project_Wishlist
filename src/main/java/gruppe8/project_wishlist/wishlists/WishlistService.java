package gruppe8.project_wishlist.wishlists;

import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.models.Wishlist;
import gruppe8.project_wishlist.repositories.WishlistRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final Logger logger;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    public List<Wishlist> getWishlistsFromUser(User user) {
        return wishlistRepository.getFromUser(user);
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
