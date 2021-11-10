package gruppe8.project_wishlist.wishlists;

import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.models.Wishlist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class WishlistController {
    private final WishlistService wishlistService;
    private final Logger logger;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @GetMapping("/home")
    public String wishlists(@RequestParam Optional<String> error,
                            @RequestParam Optional<String> success,
                            Model model,
                            Authentication authentication)
    {
        if (error.isPresent()) { model.addAttribute("error", true); }
        if (success.isPresent()) { model.addAttribute("success", true); }

        User user = (User) authentication.getPrincipal();
        List<Wishlist> wishlists = wishlistService.getWishlistsFromUser(user);
        model.addAttribute ("wishlists", wishlists);
        model.addAttribute("user", user);
        return "wishlists";
    }

    @PostMapping("/addWishlist")
    public String addWishList(WishlistCreationRequest request, Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        boolean success = wishlistService.createWishlist(user, request);

        if (success) { return "redirect:/home?success"; }
        return "redirect:/home?error";
    }



    @PostMapping("/deleteWishlist")
    public String deleteWishlist(WishlistDeletionRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        this.wishlistService.deleteWishlist(user, request);
        return "redirect:/home";
    }
}

