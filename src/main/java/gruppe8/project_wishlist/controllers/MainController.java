package gruppe8.project_wishlist.controllers;

import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.models.Wishlist;
import gruppe8.project_wishlist.repository.WishListRepository;
import gruppe8.project_wishlist.services.EmailValidation;
import org.springframework.beans.factory.annotation.Autowired;
import gruppe8.project_wishlist.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class MainController {
    UserRepository userRepository = new UserRepository();
    WishListRepository wishListRepository = new WishListRepository();
    EmailValidation validator = new EmailValidation();

    private final int TEMPUSERID = 1;

    @Autowired
    private MainController() {
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute(
                "user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        );
        return "home";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/registrationSuccess")
    public String addUser(@RequestParam String fullName, @RequestParam String argon2Password,@RequestParam String email) {
        User user = new User(fullName,email,argon2Password);


        String emailToCheck = user.getEmail();
        if (validator.isEmailValid(emailToCheck)) {
            userRepository.addUserToDatabase(user);
            return "registrationSuccess";
        } else
            return "redirect:index";
    }
    // insert into wishlists (userId, name, created) VALUES  (?,?,?)
    @PostMapping("/wishlistSuccess")
    public String addWishList(@RequestParam String name, @RequestParam Date created, @RequestParam User user){
        Wishlist wishlist = new Wishlist(name,TEMPUSERID,created);

        wishListRepository.addWishListToDatabase(wishlist,user);

        return "wishlistSuccess";
    }

}
