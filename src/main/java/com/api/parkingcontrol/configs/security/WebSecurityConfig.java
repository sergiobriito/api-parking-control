package com.api.parkingcontrol.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig  {
	
	final UserDetailsServiceImpl userDetailsServiceImpl;
	
	public WebSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	};
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.httpBasic()
			.and()
			.authorizeHttpRequests()
			.requestMatchers(HttpMethod.GET,"/parking-spot/**").permitAll()
			.requestMatchers(HttpMethod.POST,"/parking-spot").permitAll()
			.requestMatchers(HttpMethod.PUT,"/parking-spot/**").hasRole("ADMIN")
			.requestMatchers(HttpMethod.DELETE,"/parking-spot/**").hasRole("ADMIN")
			.requestMatchers(HttpMethod.GET,"/user/**").permitAll()
			.requestMatchers(HttpMethod.POST,"/user").permitAll()
			.requestMatchers(HttpMethod.PUT,"/user/**").hasRole("ADMIN")
			.requestMatchers(HttpMethod.DELETE,"/user/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.csrf().disable();
		return http.build();
	};
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsServiceImpl)
			.passwordEncoder(passwordEncoder());
	};
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};
}
