package com.kimbob.pAIk.pages;

import com.kimbob.pAIk.domain.user.dto.UserSignUpDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPageController {

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new UserSignUpDTO());
        return "signup";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
