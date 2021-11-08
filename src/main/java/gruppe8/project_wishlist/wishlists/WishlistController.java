package gruppe8.project_wishlist.wishlists;

import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.models.Wish;
import gruppe8.project_wishlist.models.Wishlist;
import gruppe8.project_wishlist.repositories.WishlistRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
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

    @GetMapping("/home")
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
        model.addAttribute ("wishlists", wishlists);
        return "wishlists";
    }

    @PostMapping("/addWishlist")
    public String addWishList(
            WishlistCreationRequest request,
            Authentication authentication,
            Model model)
    {
        User user = (User) authentication.getPrincipal();
        boolean success = wishlistService.createWishlist(user, request);
        System.out.println("Wishlist added to user");

        if (success) {
            return "redirect:/home?success";
        }
        return "redirect:/home?error";
    }

    @GetMapping("/wishlist/{wishlistId}")
    public String showWishlist(@PathVariable Long wishlistId, Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Wish> wishes = wishlistService.getWishes(user, wishlistId);
        System.out.println(wishes);

        model.addAttribute("wishes", wishes);
        model.addAttribute("wishlistId", wishlistId);
        return "wish";
    }

    @PostMapping("/addWish")
    public String addWish(@RequestParam Long wishlistId, WishCreationRequest request, Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        wishlistService.createWish(request, user);

        return "redirect:/wishlist/"+wishlistId;
    }

    @PostMapping("/deleteWishlist")
    public String deleteWishlist(WishlistDeletionRequest request, Authentication authentication){
        User user = (User) authentication.getPrincipal();

        this.wishlistService.deleteWishlist(user, request);
        return "redirect:/home";
    }

    @PostMapping("/deleteWish")
    public String deleteWish(WishDeletionRequest request, Authentication authentication){
        User user = (User) authentication.getPrincipal();

        this.wishlistService.deleteWish(user, request);
        return "redirect:/wishlist/"+request.wishlistId();
    }
}

