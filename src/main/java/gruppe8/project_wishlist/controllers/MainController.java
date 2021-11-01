package gruppe8.project_wishlist.controllers;

import com.sun.tools.javac.Main;
import gruppe8.project_wishlist.models.User;
import gruppe8.project_wishlist.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
public class MainController {
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
    public String reigstration(){
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


}
