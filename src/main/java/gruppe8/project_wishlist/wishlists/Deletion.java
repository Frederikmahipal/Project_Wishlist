package gruppe8.project_wishlist.wishlists;


import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.models.Wishlist;
import gruppe8.project_wishlist.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Deletion {

@Autowired
private WishlistRepository wishlistRepository;


public void deleteWishList(Wishlist name, User user){
    this.wishlistRepository.deleteWishlistForUser(name, user);
}





}


