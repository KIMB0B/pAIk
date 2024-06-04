package com.kimbob.pAIk.pages;

import com.kimbob.pAIk.domain.user.dto.UserSignUpDTO;
import com.kimbob.pAIk.domain.user.entity.User;
import com.kimbob.pAIk.domain.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class UserPageController {

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserPageController.class);

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new UserSignUpDTO());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserSignUpDTO userSignUpDto, Model model) {
        try {
            Set<String> roleNames = Set.of("USER", "DEVELOPER");
            userService.create(userSignUpDto, roleNames);
            return "redirect:/login?signup";
        } catch (RuntimeException e) {
            model.addAttribute("errorMsg", e.getMessage());
            model.addAttribute("user", userSignUpDto);
            return "signup";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "signup", required = false) String signup,
                                @RequestParam(value="logout", required=false) String logout,
                                Model model) {
        if(error != null) model.addAttribute("errorMsg", "올바르지 않은 사용자 이름 또는 비밀번호입니다.");
        if(signup != null) model.addAttribute("signupMsg", "회원가입에 성공했습니다.");
        if(logout != null) model.addAttribute("logoutMsg", "로그아웃 되었습니다.");
        return "login";
    }
}
