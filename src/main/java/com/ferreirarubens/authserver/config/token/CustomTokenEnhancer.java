package com.ferreirarubens.authserver.config.token;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.ferreirarubens.authserver.security.model.LoggedUser;

/**
 * @auhtor Ferreira Rubens <rubensdefrancaferreira@gmail.com>
 *
 */
public class CustomTokenEnhancer implements TokenEnhancer {
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		LoggedUser loggedUser = (LoggedUser) authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>();
		additionalInfo.put("roles", loggedUser.getAuthorities().stream().map(a -> a.getAuthority()));
		additionalInfo.put("user", loggedUser.getUser());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}
}
