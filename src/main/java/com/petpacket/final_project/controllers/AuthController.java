package com.petpacket.final_project.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.relation.RoleNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petpacket.final_project.dto.JwtResponse;
import com.petpacket.final_project.dto.SignInRequest;
import com.petpacket.final_project.dto.SignUpRequest;
import com.petpacket.final_project.entities.user.ERole;
import com.petpacket.final_project.entities.user.Role;
import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.repository.user.RoleRepository;
import com.petpacket.final_project.repository.user.UserRepository;
import com.petpacket.final_project.services.UserDetailsImpl;
import com.petpacket.final_project.utils.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          RoleRepository roleRepository,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) throws RoleNotFoundException {
//    	if(!userRepository.existsByUserNameAndPassword(signInRequest.getUserName(), passwordEncoder.encode(signInRequest.getPassword()))) {
//    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username or password is incorrect");
//    	}
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUserName(), signInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
        String roleName = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) 
                .findFirst()
                .orElse("ROLE_CUSTOMER"); 

        JwtResponse res = new JwtResponse();
        res.setToken(jwt);
        res.setUserId(userDetails.getId());
        res.setUserName(userDetails.getUsername());
        res.setEmail(userDetails.getEmail());
        res.setGender(userDetails.getGender());
        res.setPhone(userDetails.getPhone());
        res.setStatus(userDetails.getStatus());
        res.setAddress(userDetails.getAddress());
        res.setRole(roleName);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username is already taken");
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email is already taken");
        }
        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        
        Optional<Role> userRole = roleRepository.findByRoleName(ERole.ROLE_CUSTOMER);
        if (userRole.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Role not found");
        }
        
        String userFullName = signUpRequest.getFullName();
        if(userFullName.isBlank() || userFullName.isEmpty()) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("name is invalid");
        }
        String userPhone = signUpRequest.getPhone();
        if(userPhone.isBlank() || userPhone.isEmpty()) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("phone is invalid");
        }
        String userAddress = signUpRequest.getAddress();
        if(userAddress.isBlank() || userAddress.isEmpty()) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("address is invalid");
        }
        Integer userGender = signUpRequest.getGender();
        if(userGender == null) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("gender is invalid");
        }
        
        User user = new User();
        user.setUserName(signUpRequest.getUserName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(hashedPassword);
        user.setRole(userRole.get());
        user.setAddress(userAddress);
        user.setPhone(userPhone);
        user.setGender(userGender);
        user.setFullName(userFullName);
        user.setStatus(signUpRequest.getStatus());
        userRepository.save(user);
        return ResponseEntity.ok("User registered success");
    }
}