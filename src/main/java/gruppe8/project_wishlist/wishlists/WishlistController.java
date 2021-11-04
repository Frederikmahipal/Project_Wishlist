package gruppe8.project_wishlist.wishlists;

import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.models.Wishlist;
import gruppe8.project_wishlist.repositories.WishlistRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class WishlistController {
    private final WishlistRepository wishlistRepository;
    private final WishlistService wishlistService;

    public WishlistController(WishlistRepository wishlistRepository, WishlistService wishlistService) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistService = wishlistService;
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
        List<Wishlist> wishlists = wishlistRepository.getFromUser(user);
        model.addAttribute("wishlists", wishlists);
        return "wishlists";
    }

    @PostMapping("/wishlists")
    public String addWishList(WishlistCreationRequest request,
                              Authentication authentication,
                              Model model)
    {
        User user = (User) authentication.getPrincipal();
        boolean success = wishlistService.createWishlist(user, request);

        if (success) {
            return "redirect:/wishlists?success";
        }
        return "redirect:/wishlists?error";
    }
}
