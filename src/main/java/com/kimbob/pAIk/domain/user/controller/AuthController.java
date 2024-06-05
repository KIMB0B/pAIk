package com.kimbob.pAIk.domain.user.controller;

import com.kimbob.pAIk.domain.user.entity.User;
import com.kimbob.pAIk.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(username, password);
        authReq.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        Authentication auth = authenticationManager.authenticate(authReq);

        SecurityContextHolder.getContext().setAuthentication(auth);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        return "로그인 성공!";
    }

    @GetMapping("/me")
    public User getCurrentUser() {
        Optional<User> userOptional = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new RuntimeException("사용자를 찾지 못했습니다.");
        }
    }
}

