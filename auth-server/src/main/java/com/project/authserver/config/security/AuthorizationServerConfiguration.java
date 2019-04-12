package com.project.authserver.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.project.authserver.service.UserDetailsServiceImpl;

@Configuration
@EnableAuthorizationServer
/*
 * @EnableAuthorizationServer enables an Authorization Server (i.e. an
 * AuthorizationEndpoint and a TokenEndpoint) in the current application
 * context.
 */
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	/*
	 * class AuthorizationServerConfigurerAdapter implements
	 * AuthorizationServerConfigurer which provides all the necessary methods to
	 * configure an Authorization server.
	 */
	private static String REALM = "MY_OAUTH_REALM";

 
    @Autowired
    TokenStore tokenStore;
    
    @Autowired
	private DataSource dataSource;
    

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	 
	@Autowired
	private UserDetailsServiceImpl userDetailService;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("permitAll()");
		security.realm(REALM + "/client");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager).userDetailsService(userDetailService);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}



}
