package com.hb.blogapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenFilter filter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager autenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { // Access control - authorisation
		http = http.cors().and().csrf().disable(); // CRSF should be disable as we are not using web browser
		
		http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
		
		http.authorizeRequests()
//			.antMatchers("/api/public/**").permitAll()
			.antMatchers("/api/**").permitAll() // unlock security
			.anyRequest().authenticated();
		
		// This line following is mandatory to enable the token validation at each request
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class); 
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { // Authentification
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
}
