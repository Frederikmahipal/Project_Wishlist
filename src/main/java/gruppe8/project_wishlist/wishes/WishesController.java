package gruppe8.project_wishlist.wishes;

import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.models.Wish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WishesController {
    private final WishService wishService;
    private final Logger logger;

    public WishesController(WishService wishService) {
        this.wishService = wishService;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @GetMapping("/wishlist/{wishlistId}")
    public String showWishlist(@PathVariable Long wishlistId, Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Wish> wishes = wishService.getWishes(user, wishlistId);

        model.addAttribute("wishes", wishes);
        model.addAttribute("wishlistId", wishlistId);
        return "wish";
    }


    @PostMapping("/addWish")
    public String addWish(@RequestParam Long wishlistId, WishCreationRequest request, Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        wishService.createWish(request, user);
        return "redirect:/wishlist/"+wishlistId;
    }

    @PostMapping("/deleteWish")
    public String deleteWish(WishDeletionRequest request, Authentication authentication){
        User user = (User) authentication.getPrincipal();

        wishService.deleteWish(user, request);
        return "redirect:/wishlist/"+request.wishlistId();
    }

}
