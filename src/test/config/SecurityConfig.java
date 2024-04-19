package com.rs.listing.Restuarant.listing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomerUsersDetailService customerUsersDetailService;

    @Autowired
    JWTFilter jwtFilter;

    @Bean
    public PasswordEncoder passcodeEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customerUsersDetailService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers( "/api-v1/admin-users/login", "/api-v1/roles/add", "/api-v1/admin-users/add", "/api-v1/admin-users/reset-password", "/api-v1/admin-users/generate-otp","/api-v1/admin-users/get-otp").permitAll() // Public endpoints
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//		 http
//         .authorizeHttpRequests(authorize -> authorize
//             .requestMatchers("users/signup", "/users/login").permitAll()
//             .anyRequest().authenticated()
//             .and()
//             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//         )
//         .rememberMe(Customizer.withDefaults());

//		 http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
