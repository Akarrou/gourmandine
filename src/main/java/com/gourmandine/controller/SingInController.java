package com.gourmandine.controller;

import com.gourmandine.entity.User;
import com.gourmandine.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Controller
public class SingInController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/sign-in")
    public String postSignIn(@ModelAttribute User user, HttpSession session, HttpServletResponse response) {

        Optional<User> optionalUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            session.setAttribute("userSession", user);
            userRepository.save(refreshSession(user, response));
            return "redirect:/";
        }
        return "redirect:/connexion";
    }

    @GetMapping("/connexion")
    public String getSignIn(Model out, HttpSession session) {
        out.addAttribute("user", new User());
        return "sign-in";
    }

    public static User refreshSession(User user, HttpServletResponse response) {
        String idSession = RandomStringUtils.randomAlphanumeric(30);
        user.setSession(idSession);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 10);
        user.setSessionExpiration(c.getTime());
        Cookie sessionCookie = new Cookie("sessionId", user.getSession());
        sessionCookie.setMaxAge(10 * 24 * 60 * 60);
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);
        return user;
    }
}
