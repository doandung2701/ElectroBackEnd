package com.example.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;


public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter{

	private static final String TOKEN_HEADER = "authorization";
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	    HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String authToken = httpRequest.getHeader(TOKEN_HEADER);
	    if (jwtService.validate(authToken)) {
	      Long userId = jwtService.getUserIdFromToken(authToken);
	      com.example.demo.model.User user = userService.findById(userId);
	      if (user != null) {
	        boolean enabled = user.isEnabled();
	        boolean accountNonExpired = true;
	        boolean credentialsNonExpired = true;
	        boolean accountNonLocked = true;
			  UserDetails userDetails = new User(user.getUsername(),user.getPassword(),enabled,accountNonExpired,credentialsNonExpired,
					  accountNonLocked,user.getAuthorities());
	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
	            null, userDetails.getAuthorities());
	        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	      }
	    }
	    filterChain.doFilter(request, response);
	  }

	
}
