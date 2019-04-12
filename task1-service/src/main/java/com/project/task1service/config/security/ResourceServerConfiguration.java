package com.project.task1service.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Value("${auth-server.url}")
	private String authEndpoint;

	@Bean
	public TokenStore tokenStore() {
		InMemoryTokenStore inMemoryToken = new InMemoryTokenStore();
		return inMemoryToken;

	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
 
        http.
         anonymous().disable()
        .requestMatchers().antMatchers("/**")
        .and().authorizeRequests()
        .antMatchers("/**").authenticated()
        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("mw/client").tokenStore(tokenStore());
	}

	@Bean
	public RemoteTokenServices tokenService() {	
		RemoteTokenServices tokenServices = new RemoteTokenServices();
		tokenServices.setClientId("client");
		tokenServices.setClientSecret("secret");
		tokenServices.setCheckTokenEndpointUrl(authEndpoint + "/uaa/oauth/check_token");
		return tokenServices;
	}

}
