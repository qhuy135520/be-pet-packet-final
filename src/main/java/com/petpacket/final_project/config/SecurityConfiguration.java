package com.petpacket.final_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.petpacket.final_project.dto.request.authentication.AuthTokenFilter;
import com.petpacket.final_project.services.AuthEntryPointJwt;
import com.petpacket.final_project.services.user.UserDetailsServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
	private UserDetailsServiceImpl userDetailsService;
	private AuthEntryPointJwt authEntryPointJwt;
	private AuthTokenFilter authTokenFilter;
	private CustomAccessDeniedHandler customAccessDeniedHandler;

	public SecurityConfiguration(UserDetailsServiceImpl userDetailsService, AuthEntryPointJwt authEntryPointJwt,
			AuthTokenFilter authTokenFilter, CustomAccessDeniedHandler customAccessDeniedHandler) {
		this.userDetailsService = userDetailsService;
		this.authEntryPointJwt = authEntryPointJwt;
		this.authTokenFilter = authTokenFilter;
		this.customAccessDeniedHandler = customAccessDeniedHandler;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	@Primary
	public AuthenticationManagerBuilder configureAuthenticationManagerBuilder(
			AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		return authenticationManagerBuilder;
	}

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
				.authenticationEntryPoint(authEntryPointJwt).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.requestMatchers("api/cities").permitAll()
				.requestMatchers("/api/auth/**").permitAll()
				.requestMatchers("api/types/**").permitAll()
				.requestMatchers("api/pets/**").permitAll()
				.requestMatchers("api/blogs/**").permitAll()
				.requestMatchers("/api/cities").permitAll()
				.requestMatchers("/api/services/**").permitAll()
				.requestMatchers("/api/auth/**").permitAll()
				.requestMatchers("/api/review/**").permitAll()
				.requestMatchers("/api/booking/**").permitAll()
				.requestMatchers("/api/users/**").permitAll()
				.requestMatchers("/api/admin/users/**").permitAll()
				.requestMatchers("/api/admin/reviews/**").permitAll()
				.requestMatchers("/api/admin/upgrades/**").permitAll()
				.requestMatchers("/api/admin/revenue-management/**").permitAll()
				.requestMatchers("/api/admin/services-management/**").permitAll()
				.requestMatchers("/api/admin/booking-management/**").permitAll()
				.requestMatchers("/api/upload/**").permitAll()
				.requestMatchers("/api/upgrade-request/**").permitAll()
				.requestMatchers("/api/payment/**").permitAll()
				.requestMatchers("/api/weight-category/**").permitAll()
				.requestMatchers("/api/service-detail/**").permitAll()
				.requestMatchers("/api/service-addons/**").permitAll()
				.requestMatchers("/api/transaction/**").permitAll()
				.requestMatchers("/api/transaction-detail/**").permitAll()
				.requestMatchers("/api/discount-code/**").permitAll()
				.requestMatchers("/api/test/**").permitAll().anyRequest().authenticated();
		http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
