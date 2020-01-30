package com.gourmandine.controller;

import com.gourmandine.entity.User;
import com.gourmandine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class SingInController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/sign-in")
    public String postSignIn(@ModelAttribute User user, HttpSession session) {

        Optional<User> optionalUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            session.setAttribute("userSession", user);
            return "redirect:/";
        }
        return "redirect:/connexion";
    }

    @GetMapping("/connexion")
    public String getSignIn(Model out, HttpSession session) {
        out.addAttribute("user", new User());
        return "sign-in";
    }
}
