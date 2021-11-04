package gruppe8.project_wishlist.controllers;

import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.models.Wishlist;
import gruppe8.project_wishlist.repositories.WishlistRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class WishlistController {
    private final WishlistRepository wishlistRepository;

    public WishlistController(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @GetMapping("/wishlists")
    public String wishlists(
            @RequestParam Optional<String> error,
            @RequestParam Optional<String> success,
            Model model,
            Authentication authentication)
    {
        if (error.isPresent()) { model.addAttribute("error", true); }
        if (success.isPresent()) { model.addAttribute("success", true); }

        User user = (User) authentication.getPrincipal();
        List<Wishlist> wishlists = wishlistRepository.getWishlistsFromUser(user);
        model.addAttribute("wishlists", wishlists);
        return "wishlists";
    }

    @GetMapping("/wish")
    public String addWishToList(){
        return "wish";  
    }

    @PostMapping("/wishlists?add")
    public String addWishList(@RequestParam String name,
                              Authentication authentication,
                              Model model)
    {
        User user = (User) authentication.getPrincipal();
        boolean success = wishlistRepository.createWishlistForUser(user);

        if (success) {
            return "redirect:/wishlists?success";
        }
        return "redirect:/wishlist?error";
    }
}
