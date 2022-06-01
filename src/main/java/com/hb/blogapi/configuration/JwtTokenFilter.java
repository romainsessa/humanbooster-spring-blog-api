package com.hb.blogapi.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	private Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		logger.info("On the JwtTokenFilter !");
		
		String header = request.getHeader(HttpHeaders.AUTHORIZATION.toLowerCase());
		if(header == null || header.isEmpty() || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		// Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6ImNvbS5oYiIsImlhdCI6MTY1NDA5NDEyOCwiZXhwIjoxNjU0Njk4OTI4fQ.RPUAUbjuOilDxLxhumtgXvezvdpEFvmbeJol7-0c1VbAxv11Ez59bukQm1ahXSlbKn1QiOEgWZDarRm7QaK8jQ
		// [O] : Bearer
		// [1] : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6ImNvbS5oYiIsImlhdCI6MTY1NDA5NDEyOCwiZXhwIjoxNjU0Njk4OTI4fQ.RPUAUbjuOilDxLxhumtgXvezvdpEFvmbeJol7-0c1VbAxv11Ez59bukQm1ahXSlbKn1QiOEgWZDarRm7QaK8jQ
		String token = header.split(" ")[1].trim();
		
		logger.info("Extracted token is : " + token);
		
		if(!jwtTokenUtil.validate(token)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String username = jwtTokenUtil.getUsername(token);
		UserDetails user = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				user, null, user.getAuthorities()		
				);
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}

}
