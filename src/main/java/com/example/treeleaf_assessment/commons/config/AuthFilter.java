package com.example.treeleaf_assessment.commons.config;

import com.example.treeleaf_assessment.commons.exception.BadRequestException;
import com.example.treeleaf_assessment.entity.user.Authority;
import com.example.treeleaf_assessment.entity.user.User;
import com.example.treeleaf_assessment.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userValidator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
            authorization = authorization.replace("Bearer ", "");
            String usernameFromToken = jwtTokenUtil.getUsernameFromToken(authorization);
            User user = userValidator.validateUserByEmail(usernameFromToken.toLowerCase()).orElseThrow(()-> new BadRequestException("USR002", "The provided login credentials are invalid."));
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (user.getGroup() != null) {
                for (Authority authority : user.getGroup().getAuthorities()) {
                    authorities.add(new SimpleGrantedAuthority(authority.getName()));
                }
            }
            CustomAuthentication authenticationToken = new CustomAuthentication(user);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

}
