package io.github.leynerbueno.alura_courses.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.github.leynerbueno.alura_courses.service.UserService;
import lombok.RequiredArgsConstructor;
import io.github.leynerbueno.alura_courses.entity.UserEntity;
import io.github.leynerbueno.alura_courses.secutiry.CustomAuthentication;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String pwd = (String) authentication.getCredentials();

        UserEntity entity = userService.findByUsername(username);

        if (entity != null) {
            boolean isPasswordValid = passwordEncoder.matches(pwd, entity.getPassword());

            if (isPasswordValid) {
                return new CustomAuthentication(entity);
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
