package com.spring.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class MyController {
	
	 
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/user")
	public ResponseEntity<String> user(){
		
		return ResponseEntity.ok("yes , i am user");
		
		
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public ResponseEntity<String> admin(){
		
		
		return ResponseEntity.ok("yes , i am admin");
		
	}
	
	
	

}
