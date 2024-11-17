package com.scm.demo.config;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.demo.Entities.Providers;
import com.scm.demo.Entities.User;
import com.scm.demo.Utilities.AppConstants;
import com.scm.demo.repo.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    Logger log = LoggerFactory.getLogger(OAuthSuccessHandler.class);

    @Autowired
    private UserRepo userRepo;


    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, 
        HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        log.info("in OAuthSuccessHandler");

        //Identfy the provider
        var oAuth2AuthToken = (OAuth2AuthenticationToken) authentication;
        String provider = oAuth2AuthToken.getAuthorizedClientRegistrationId();
        
        log.info("provider : " + provider);

        //if provider is google
        if (provider.equals("google")) {
                // log.info("provider is google");
                // save user data in DB
            DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
            log.info("user name : " + user.getName());
            user.getAttributes().forEach((key, value) -> {
                log.info(key + " : " + value);
            });
            String email = user.getAttributes().get("email").toString();
            User userFromDB = userRepo.findByEmail(email).orElse(null);
            if (userFromDB != null) {
                log.info("user already exists");
                    new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
                    return;
            }
            User userToRegister = new User();
            userToRegister.setName(user.getAttributes().get("name").toString());
            userToRegister.setEmail(user.getAttributes().get("email").toString());
            userToRegister.setPassword("Password");
            userToRegister.setEmailVarified(true);
            userToRegister.setProvider(Providers.GOOGLE);
            userToRegister.setProfilePicLink(user.getAttributes().get("picture").toString());
            userToRegister.setEnabled(true);
            userToRegister.setProviderUserId(user.getName());
            userToRegister.setRoleList(List.of(AppConstants.ROLE_USER));
            log.info("userToRegister : " + userToRegister);
            userRepo.save(userToRegister);
            
            log.info("autuhorities : "+user.getAuthorities());
        }

        //if provider is github
        if (provider.equals("github")) {
            log.info("provider is github");
            // save user data in DB
            DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
            log.info("user name : " + user.getName());
            user.getAttributes().forEach((key, value) -> {
                log.info(key + " : " + value);
            });
            String email = user.getAttributes().get("email").toString();
            User userFromDB = userRepo.findByEmail(email).orElse(null);
            if (userFromDB != null) {
                log.info("user already exists");
                    new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
                    return;
            }
            User userToRegister = new User();
            userToRegister.setName(user.getAttributes().get("name").toString());
            userToRegister.setEmail(user.getAttributes().get("email").toString());
            userToRegister.setAbout(user.getAttributes().get("bio").toString());
            userToRegister.setPassword("Password");
            userToRegister.setEmailVarified(true);
            userToRegister.setProvider(Providers.GITHUB);
            userToRegister.setProfilePicLink(user.getAttributes().get("avatar_url").toString());
            userToRegister.setEnabled(true);
            userToRegister.setProviderUserId(user.getName());
            userToRegister.setRoleList(List.of(AppConstants.ROLE_USER));
            log.info("userToRegister : " + userToRegister);
            userRepo.save(userToRegister);
        }

        // will create one screen to add all remainng details
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }

}
