package com.rs.listing.Restuarant.listing.config;

import com.rs.listing.Restuarant.listing.serviceImpl.UserServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(JWTFilter.class);
    @Autowired
    private JWTUtill jwtutill;

    @Autowired
    private CustomerUsersDetailService service;

    Claims claims = null;
    private String userName = null;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        if(request.getServletPath().matches("/users/login")) {
            filterChain.doFilter(request, response);
        }else {
            String authorizationheader = request.getHeader("Authorization");
            String token = null;
            if(authorizationheader !=null && authorizationheader.startsWith("Bearer ")) {
                token = authorizationheader.substring(7);
                userName = jwtutill.extractUsername(token);
                claims = jwtutill.extractAllClaims(token);

            }
            if(userName !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = service.loadUserByUsername(userName);
                log.info("userdetails {}", userDetails);
                if(jwtutill.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        }

    }
//    public boolean isAdmin() {
//
//        return "admin".equalsIgnoreCase((String) claims.get("role"));
//    }

    public String getRole(){
        logger.info("get Role {}",  claims.get("role"));
        return (String) claims.get("role");
    }

//    public boolean isUser() {
//        return "user".equalsIgnoreCase((String) claims.get("role"));
//    }
//    public boolean isSuper_admin() {return "super_admin".equalsIgnoreCase((String) claims.get("role"));}

    public String getCurrentUser() {
        return userName;
    }
}
