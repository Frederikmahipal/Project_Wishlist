package gruppe8.project_wishlist.signup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class SignupController {
    private final SignupService registrationService;
    private final Logger logger;

    public SignupController(SignupService registrationService) {
        this.registrationService = registrationService;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @GetMapping("/signup")
    public String registration(@RequestParam Optional<String> error, Model model){
        if (error.isPresent()) {
            logger.info("received request with error parameter");
            model.addAttribute("error", true);
        }
        return "signup";
    }

    @PostMapping("/signup")
    public String addUser(SignupRequest request) {
        Boolean success = registrationService.register(request);
        if (success) {
            return "redirect:/login?signupSuccess";
        }
        return "redirect:/signup?error";
    }

}