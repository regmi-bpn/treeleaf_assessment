package com.example.treeleaf_assessment.commons.config;

import com.example.treeleaf_assessment.entity.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomAuthentication implements Authentication {

    private final String name;
    private final String userId;
    private final String email;
    private final List<SimpleGrantedAuthority> permissions;
    private boolean authenticated = true;

    public CustomAuthentication(User userEntity) {
        this.userId = userEntity.getId();
        this.email = userEntity.getEmail();
        this.name = userEntity.getName();
        if (userEntity.getGroup() != null && userEntity.getGroup().getAuthorities() != null)
            this.permissions = userEntity.getGroup().getAuthorities().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getName())).collect(Collectors.toList());
        else this.permissions = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public Object getCredentials() {
        return this.email;
    }

    @Override
    public Object getDetails() {
        return this.userId;
    }

    public String getUserId() {
        return this.userId;
    }

    @Override
    public Object getPrincipal() {
        return this.email;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return name;
    }

}
