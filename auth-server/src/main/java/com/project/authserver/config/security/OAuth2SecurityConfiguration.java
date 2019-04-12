package com.project.authserver.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.project.authserver.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private DataSource dataSource;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().anonymous().disable().authorizeRequests().antMatchers("/oauth/token").permitAll();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.eraseCredentials(false).userDetailsService(userDetails()).passwordEncoder(encoder);
	}

	@Bean
	public UserDetailsService userDetails() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public TokenStore tokenStore() {
		JdbcTokenStore tokenStore= new JdbcTokenStore(dataSource);
		//tokenStore.setAuthenticationKeyGenerator(new UniqueAuthenticationKeyGenerator());
		return tokenStore;
	}

}
