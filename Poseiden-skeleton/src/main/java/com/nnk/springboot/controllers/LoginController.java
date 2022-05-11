package com.nnk.springboot.controllers;

import com.nnk.springboot.util.MyUserPrincipal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Invalid username and password.");
            LOGGER.error("Access to 403 page. Invalid username and password.");
            return "/error/403";
        }
        LOGGER.info("Access to login page.");
        return "login";
    }


    @RequestMapping("/loginsuccess")
    public String getUserInfo(Principal user, HttpSession session) {
        StringBuilder userInfo = new StringBuilder();
        if (user instanceof UsernamePasswordAuthenticationToken) {
            session.setAttribute("name", userInfo.append(getUsernamePasswordLoginInfo(user)));
            LOGGER.info("User name {} successfully retrieved.", userInfo);
        } else if (user instanceof OAuth2AuthenticationToken) {
            session.setAttribute("name", userInfo.append(getOauth2LoginInfo(user)));
            LOGGER.info("OAuth2 User name {} successfully retrieved.", userInfo);
        }
        LOGGER.info("User successfully logged in.");
        return "redirect:/bidList/list";
    }

    private StringBuilder getUsernamePasswordLoginInfo(Principal user) {
        StringBuilder usernameInfo = new StringBuilder();

        UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
        if (token.isAuthenticated()) {
            MyUserPrincipal u = (MyUserPrincipal) token.getPrincipal();
            usernameInfo.append(u.getUsername());
        } else {
            usernameInfo.append("NA");
        }
        return usernameInfo;
    }

    private StringBuilder getOauth2LoginInfo(Principal user) {

        StringBuilder protectedInfo = new StringBuilder();

        OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
        if (authToken.isAuthenticated()) {

            Map<String, Object> userAttributes = authToken.getPrincipal().getAttributes();

            protectedInfo.append(userAttributes.get("name"));

        }
        return protectedInfo;
    }

}

