package com.ncs.abc.web;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.abc.dto.AppUserRequestDTO;
import com.ncs.abc.dto.JWTResponseDTO;
import com.ncs.abc.jwtutil.JWTUtil;



@RestController
@RequestMapping("/fb/public")
public class PublicController {

	@Autowired
	private com.ncs.abc.service.AppUserServiceImpl appUserServiceImpl;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	
	public PublicController() {
		System.out.println(" ---->> Inside JWT Public Controller ");
	}



	@GetMapping("/login/{username}/{password}")
	public String doLogin(@PathVariable String username, @PathVariable String password)throws Exception
	{
		System.err.println("\n\n----->> inside public/login "+username+" and "+password);
		
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			System.out.println("");
			
		} catch (Exception e) {
			throw new Exception("Bad credentials ");
		}

		UserDetails userDetails =  appUserServiceImpl.loadUserByUsername(username);
		
		String token = jwtUtil.generateToken(userDetails);
		
		boolean isValid = token!=null?true:false;
		
		if(isValid) return token;
		else return "error in token generation";
	}
}
