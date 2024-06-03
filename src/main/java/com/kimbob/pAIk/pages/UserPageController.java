package com.kimbob.pAIk.pages;

import com.kimbob.pAIk.domain.user.dto.UserSignUpDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserPageController {

    private static final Logger log = LoggerFactory.getLogger(UserPageController.class);

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new UserSignUpDTO());
        return "signup";
    }

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value="logout", required=false) String logout,
                                Model model) {

        if(error != null) {
            model.addAttribute("errorMsg", "올바르지 않은 사용자 이름 또는 비밀번호입니다.");
        }

        if(logout != null) {
            model.addAttribute("logoutMsg", "로그아웃 되었습니다.");
        }
        return "login";
    }
}
