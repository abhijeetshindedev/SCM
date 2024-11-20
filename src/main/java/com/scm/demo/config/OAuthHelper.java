package com.scm.demo.config;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

@Component
public class OAuthHelper {

    static Logger log = LoggerFactory.getLogger(OAuthHelper.class);
    
        public static String getEmailFromPrincipal(Principal principal) {
            Authentication authentication = (Authentication) principal;
            String email = "";
            // Check weather its Google or Github User
            if (authentication instanceof OAuth2AuthenticationToken){
                DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
                // Take email from user
                email = user.getAttributes().get("email").toString();
                log.info("user name : " + email);
            }
            else{
                email = principal.getName();
            }
        return email;
    }

}
