package com.interswitch.codingassessment.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interswitch.codingassessment.security.service.CustomUserDetailsService;
import com.interswitch.codingassessment.security.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author bkaaron
 * @Project coding-assessment
 * @Date 7/29/23
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException, java.io.IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        String username = "";

        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        try {
            username = jwtService.extractUsername(jwt);
        } catch (ExpiredJwtException e) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            final Map<String, Object> body = new HashMap<>();
            body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
            body.put("error", "Unauthorized");
            body.put("message", "JWT has expired");
            body.put("path", request.getServletPath());

            final ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), body);
        }catch (IllegalArgumentException e) {
            /*response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            final Map<String, Object> body = new HashMap<>();
            body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
            body.put("error", "Unauthorized");
            body.put("message", "an error occured during getting username from token");
            body.put("path", request.getServletPath());

            final ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), body);*/
        }

        if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
                //SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}

