package com.institution.manager.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import static com.institution.manager.util.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader(HEADER_STRING);

        if (header != null && header.startsWith(TOKEN_PREFIX)){
            chain.doFilter(request, response);
        }

        UsernamePasswordAuthenticationToken auth = getAuth(request);
        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuth(HttpServletRequest req){
        String token = req.getHeader(HEADER_STRING);
        if (token != null){
            String auth = JWT.require(Algorithm.HMAC512(SECRET_KEY.getBytes()))
                    .build().verify(token.replace(TOKEN_PREFIX, "")).getSubject();
            if(auth != null)
                return new UsernamePasswordAuthenticationToken(auth, null, new ArrayList<>());
        }
        return null;
    }

}
