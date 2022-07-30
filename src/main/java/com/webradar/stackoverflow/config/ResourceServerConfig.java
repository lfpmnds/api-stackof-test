package com.webradar.stackoverflow.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private org.springframework.core.env.Environment env;
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	private static final String[] LOGIN_OR_NEW_OR_VIEWER = { "/oauth/token" , "/users/new", "/h2-console/**" };
	
	private static final String[] PUBLIC = { "/questions", "/answers" };
	
	private static final String[] USER = { "/questions/**", "/answers/**", "/questions/{id}/**", "/answers/{id}/**" };
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		// H2
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.authorizeRequests()
		.antMatchers(LOGIN_OR_NEW_OR_VIEWER).permitAll()
		.antMatchers(HttpMethod.GET, PUBLIC).permitAll()
		.antMatchers(HttpMethod.POST, USER).hasAuthority("USER")
		.antMatchers(HttpMethod.PUT, USER).hasAuthority("USER")
		.anyRequest().authenticated();
	}	
}
