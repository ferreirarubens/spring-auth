package com.ferreirarubens.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ferreirarubens.authserver.security.service.CustomUserDetailsService;

/**
 * @author rubens.ferreira
 *
 */
@Configuration
@EnableWebSecurity
@Order(100)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		 http.requestMatchers()
         	.antMatchers("/**")
         	.and()
         	.authorizeRequests()
         	.anyRequest()
         	.authenticated()
         	.antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
         	.antMatchers(HttpMethod.OPTIONS, "/**").access("#oauth2.hasScope('read')")
         	.antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
         	.antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
         	.antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')")
         	.antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')");
		// Apenas para testes com H2
		http.csrf().disable();
		http.headers().frameOptions().disable();

	}

}