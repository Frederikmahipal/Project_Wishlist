package gruppe8.project_wishlist.controllers;

import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.models.Wishlist;
import gruppe8.project_wishlist.repository.WishListRepository;
import gruppe8.project_wishlist.services.EmailValidation;
import gruppe8.project_wishlist.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import gruppe8.project_wishlist.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

@Controller
public class MainController {
    UserRepository userRepository = new UserRepository();
    WishListRepository wishListRepository = new WishListRepository();
    EmailValidation validator = new EmailValidation();

    private final int TEMPUSERID = 1;

    private final UserAuthenticationService authService;

    @Autowired
    private MainController(UserAuthenticationService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("login")
    public String login(@RequestParam String email, @RequestParam String password, HttpServletResponse response) {
        User user = authService.authenticateUser(email, password);
        if (user == null) {
            System.out.println("login error");
            return "index";
        }
        System.out.println("login success");
        return "index";
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
