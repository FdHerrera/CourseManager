package com.institution.manager.config;

import com.institution.manager.config.jwt.JWTAuthenticationFilter;
import com.institution.manager.config.jwt.JWTAuthorizationFilter;
import com.institution.manager.enumerate.ERole;
import com.institution.manager.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.institution.manager.enumerate.ERole.*;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceImpl userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/register", "/auth/login").permitAll().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/auth/confirm_token").permitAll().and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/course/create_course").hasAnyRole(ROLE_ADMIN.name()).and().authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/course/set_professor").hasAnyRole(ROLE_ADMIN.name()).and().authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/course/subscribe_to_course").hasAnyRole(ROLE_STUDENT.name())
                .anyRequest().authenticated().and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager())).addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
