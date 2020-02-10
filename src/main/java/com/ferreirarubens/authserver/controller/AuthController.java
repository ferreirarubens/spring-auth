/**
 * 
 */
package com.ferreirarubens.authserver.controller;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @auhtor Ferreira Rubens <rubensdefrancaferreira@gmail.com>
 *
 */
@RestController
@RequestMapping("/")
public class AuthController {

	@GetMapping("/user")
	public Map<String, String> user(Principal principal) {
	    if (principal != null) {
	        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
	        Authentication authentication = oAuth2Authentication.getUserAuthentication();
	        Map<String, String> details = new LinkedHashMap<>();
	        details = (Map<String, String>) authentication.getDetails();
	        Map<String, String> map = new LinkedHashMap<>();
	        map.put("username", details.get("username"));
	        return map;
	    }
	    return null;
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("forward:/index.html");
	}
}
