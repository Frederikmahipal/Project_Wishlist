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

        model.addAttribute("wishes", wishes);
        model.addAttribute("wishlistId", wishlistId);
        return "wish";
    }

    @PostMapping("/addWish")
    public String addWish(@RequestParam Long wishlistId, WishCreationRequest request, Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        wishlistService.createWish(request, user);
        System.out.println("request.wishlistId() = " + request.wishlistId());
        System.out.println("request.title() = " + request.title());
        System.out.println("request.url() = " + request.url());
        System.out.println("request.price() = " + request.price());
        System.out.println("request.note() = " + request.note());
        System.out.println("request.image().getOriginalFilename() = " + request.image().getOriginalFilename());

        return "redirect:/wishlist/"+wishlistId;
    }


    /*
    @GetMapping("/deleteWishlist/{name}")
    public String deleteWisList(@PathVariable (value = "name") User name, User user){
        this.wishlistService.deleteWishlist(name, user);
        return "redirect:/home";
    }
    */

    //public boolean deleteWishlistForUser(Wishlist wishlist, User user) {
    @PostMapping("/deleteWishlist")
    public String deleteWishlist(WishlistDeletionRequest request, Authentication authentication){
        User user = (User) authentication.getPrincipal();

        this.wishlistService.deleteWishlist(user, request);
        return "redirect:/home";
    }
}

