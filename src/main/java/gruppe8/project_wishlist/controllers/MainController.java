package gruppe8.project_wishlist.controllers;

import gruppe8.project_wishlist.repository.WishListRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/registration")
    public String reigstration(){
        return "registration";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }



}
