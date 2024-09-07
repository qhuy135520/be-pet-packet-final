package com.petpacket.final_project.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.petpacket.final_project.dto.authentication.JwtResponse;
import com.petpacket.final_project.dto.authentication.SignInRequest;
import com.petpacket.final_project.dto.authentication.UserResponse;
import com.petpacket.final_project.services.user.UserDetailsImpl;
import com.petpacket.final_project.utils.JwtUtil;

@Service
public class SignInService {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	
	public JwtResponse signin(SignInRequest signInRequest) throws AuthenticationException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtil.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String roleName = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst()
                    .orElse("ROLE_CUSTOMER");

            UserResponse userResponse = new UserResponse();
            userResponse.setUserId(userDetails.getId());
            userResponse.setUsername(userDetails.getUsername());
            userResponse.setEmail(userDetails.getEmail());
            userResponse.setGender(userDetails.getGender());
            userResponse.setPhone(userDetails.getPhone());
            userResponse.setStatus(userDetails.getStatus());
            userResponse.setAddress(userDetails.getAddress());
            userResponse.setRole(roleName);
            userResponse.setName(userDetails.getName());

            JwtResponse res = new JwtResponse();
            res.setAccess_token(jwt);
            res.setUser(userResponse);

            return res;
        } catch (AuthenticationException e) {
            throw e;
        }
    }
}
