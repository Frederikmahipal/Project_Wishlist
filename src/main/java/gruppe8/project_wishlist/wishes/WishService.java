package gruppe8.project_wishlist.wishes;

import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.models.Wish;
import gruppe8.project_wishlist.models.Wishlist;
import gruppe8.project_wishlist.repositories.WishRepository;
import gruppe8.project_wishlist.repositories.WishlistRepository;
import gruppe8.project_wishlist.wishlists.WishlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WishService {
    private final WishlistRepository wishlistRepository;
    private final WishRepository wishRepository;
    private final Logger logger;

    public WishService(WishlistRepository wishlistRepository, WishRepository wishRepository) {
        this.wishlistRepository = wishlistRepository;
        this.wishRepository = wishRepository;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    public boolean createWish(WishCreationRequest request, User user) {
        boolean userOwnsWishlist = wishlistRepository.userOwnsWishlist(user, request.wishlistId());
        if (!userOwnsWishlist) { return false; }

        Wishlist wishlist = new Wishlist(null, request.wishlistId(), null); //ew

        Wish wish = new Wish();
        wish.setPrice(request.price());
        wish.setCreated(LocalDateTime.now());
        wish.setNote(request.note());
        wish.setTitle(request.title());
        wish.setUrl(request.url());

        return wishRepository.createWish(wishlist, wish);
    }

    public List<Wish> getWishes(User user, Long wishlistId) {
        boolean userOwnsWishlist = wishlistRepository.userOwnsWishlist(user, wishlistId);
        if (!userOwnsWishlist) {
            logger.warn("User " + user.getId() + "tried to get list of wishes from another user.");
            return null;
        }

        Wishlist wishlist = new Wishlist(null, wishlistId, null); // ew
        return wishRepository.getAllWishesFromWishlist(wishlist);
    }

    public boolean deleteWish(User user, WishDeletionRequest request) {
        boolean userOwnsWishlist = wishlistRepository.userOwnsWishlist(user, request.wishlistId());
        if (!userOwnsWishlist) { return false; }

        Wish wish = new Wish();
        wish.setId(request.wishId());


        return wishRepository.deleteWishFromWishlist(wish);
    }

}
