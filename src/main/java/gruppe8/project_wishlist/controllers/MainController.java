package gruppe8.project_wishlist.controllers;

import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.repositories.WishlistRepository;
import gruppe8.project_wishlist.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final Logger logger;

    private MainController() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @GetMapping("/")
    public String index(Model model, Authentication authentication){
        User user = null;
        if (authentication != null) {
            user = (User) authentication.getPrincipal();
        }
        model.addAttribute("user", user);
        return "index";
    }

}
