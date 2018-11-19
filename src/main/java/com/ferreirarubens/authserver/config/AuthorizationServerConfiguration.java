package com.ferreirarubens.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.ferreirarubens.authserver.config.token.CustomTokenEnhancer;

/**
 * @author rubens.ferreira
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Value("${security.oauth2.client.client-id}")
	private String clientId;

	@Value("${security.oauth2.client.authorized-grant-types}")
	private String[] authorizedGrantTypes;

	@Value("${security.oauth2.client.resource-ids}")
	private String resourceIds;

	@Value("${security.oauth2.client.scope}")
	private String[] scopes;

	@Value("${security.oauth2.client.client-secret}")
	private String secret;

	@Value("${security.oauth2.token-store.key}")
	private String key;

	@Value("${security.oauth2.client.access-token-validity-seconds}")
	private Integer accessTokenValiditySeconds;

	@Value("${security.oauth2.client.refresh-token-validity-seconds}")
	private Integer refreshTokenValiditySeconds;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		endpoints.authenticationManager(this.authenticationManager).tokenStore(this.tokenStore()).tokenEnhancer(tokenEnhancerChain);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
			.withClient(clientId)
			.secret(encoder().encode(secret))
			.authorizedGrantTypes(authorizedGrantTypes)
			.scopes(scopes)
			.resourceIds(resourceIds)
			.accessTokenValiditySeconds(accessTokenValiditySeconds)
			.refreshTokenValiditySeconds(refreshTokenValiditySeconds);
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

}