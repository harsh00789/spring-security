package com.spring.security.config;

import java.awt.image.AffineTransformOp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class Myconfig {

	
	



	
	
	@Bean
	public UserDetailsService userdetailservice() {
		
		//first method
		
//		UserDetails user = User.builder()
//				.username("harsh")
//				.password(passenc().encode("1234"))
//				.roles("USER")
//				.build();
//	
//		UserDetails admin = User.builder()
//				.username("harsht")
//				.password(passenc().encode("1234"))
//				.roles("USER","ADMIN")
//				.build();
//		
		
		//second method
		
		
		UserDetails user = User.withUsername("harsh")
				                .password(passenc().encode("1234"))
				                .roles("USER")
				                .build();
				
		UserDetails admin = User.withUsername("thaker")
				              .password(passenc().encode("1234"))
				              .roles("ADMIN")
				              .build();
		
		
		
		
		return new InMemoryUserDetailsManager(user,admin);
		
		
		
	}
	
	
	@Bean PasswordEncoder passenc() {
		
		return new BCryptPasswordEncoder();
		
	}
	
	
@Bean 	
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	
	//here all home/user/ are not need to login
	
	// home/admin needs to login first after that that are check at rest api also 
	// for user has role of admin of not after that can access
	
	
	
	//at rest api of user u can see @preauthorize(has_Role('ADMIN')) if user has /home/user
    // than they can be access from here but it again check by rest api by above annotation 
	// that user has role of admin means admin username and password.
	
	
	http.csrf().disable()
	.authorizeHttpRequests()
	.requestMatchers("home/user/**")
	//.hasRole("USER")
   // .requestMatchers("home/admin/**")
	.permitAll()
	.anyRequest()
	.authenticated()
	.and()
	
	
	.formLogin(
			form -> form
			
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/welcom")
			.permitAll()
			);
	  
	
	          
	
	
	return http.build();
	
}
	
	
	
}
