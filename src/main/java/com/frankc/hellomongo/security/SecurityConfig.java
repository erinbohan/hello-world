package com.frankc.hellomongo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.frankc.hellomongo.apikey.ApiKeyRepo;
import com.frankc.hellomongo.apikey.KeyNotFoundException;


@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/* Authentication : User --> Roles
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("user1").password(encoder.encode("secret1"))
				.roles("USER").and().withUser("admin1").password(encoder.encode("secret1"))
				.roles("USER", "ADMIN");
	}

	// Authorization : Role -> Access
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers("/links/**")
				.hasRole("USER").antMatchers("/**").hasRole("ADMIN").and()
				.csrf().disable().headers().frameOptions().disable();
	}*/
	
	@Autowired
	private ApiKeyRepo apiKeyRepo;

	//@Value("${yourapp.http.auth-token-header-name}")
	private String principalRequestHeader = "HELLO-API-KEY";

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		APIKeyAuthFilter filter = new APIKeyAuthFilter(principalRequestHeader);
		filter.setAuthenticationManager(new AuthenticationManager() {

			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				String principal = (String) authentication.getPrincipal();
				try {
					apiKeyRepo.get(principal);
				}
				catch(KeyNotFoundException ex){
					throw new BadCredentialsException("The API key was not found or not the expected value.");
				}
				authentication.setAuthenticated(true);
				return authentication;
			}
		});
		httpSecurity.
		antMatcher("/links/**").
			csrf().disable().
			sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
			and().addFilter(filter).authorizeRequests().anyRequest().authenticated();
		}
}