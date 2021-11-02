package gruppe8.project_wishlist.controllers;

import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.repository.UserRepository;
import gruppe8.project_wishlist.repository.WishListRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    UserRepository userRepository = new UserRepository();


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

    @PostMapping("/registrationSuccess")
    public String addUser(@ModelAttribute User user){
        userRepository.addUserToDatabase(user);
        return "registrationSuccess";
    }



}
