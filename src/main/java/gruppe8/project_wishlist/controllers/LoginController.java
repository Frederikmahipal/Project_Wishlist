package gruppe8.project_wishlist.controllers;

import gruppe8.project_wishlist.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(@RequestParam Optional<String> error,
                        @RequestParam Optional<String> signupSuccess,
                        @RequestParam Optional<String> success,
                        Model model)
    {
        if (success.isPresent()) { return "redirect:/home"; }
        if (error.isPresent()) { model.addAttribute("error", true); }
        if (signupSuccess.isPresent()) { model.addAttribute("signupSuccess", true); }
        return "login";
    }
}
