package com.example.buyer.user;

import com.example.buyer.product.ProductResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    @PostMapping("/login")
    public String login() {
        return "redirect:/";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO) {
        userService.save(reqDTO.toEntity());

        return "redirect:/";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

}
