package gruppe8.project_wishlist.controllers;

import gruppe8.project_wishlist.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

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
    public String reigstration(){
        return "registration";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


}
