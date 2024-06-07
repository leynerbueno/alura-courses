package io.github.leynerbueno.alura_courses.secutiry;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.github.leynerbueno.alura_courses.entity.UserEntity;

public class CustomAuthentication implements Authentication {

    private final UserEntity userEntity;

    public CustomAuthentication(UserEntity userEntity) {
        if (userEntity == null) {
            throw new ExceptionInInitializerError("user is required");
        }
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority(this.userEntity.getRole().name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getName() {
        return this.userEntity.getUsername();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.userEntity;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        
    }

}
