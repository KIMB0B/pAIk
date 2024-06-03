package com.kimbob.pAIk.pages;

import com.kimbob.pAIk.domain.user.entity.User;
import com.kimbob.pAIk.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomePageController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            Optional<User> optionalUser = userRepository.findByUsername(userDetails.getUsername());
            optionalUser.ifPresent(user -> model.addAttribute("user", user));
        }
        return "home";
    }
}
