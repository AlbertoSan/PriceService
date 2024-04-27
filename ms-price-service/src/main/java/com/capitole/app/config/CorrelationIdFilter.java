package com.capitole.app.config;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CorrelationIdFilter extends OncePerRequestFilter {

    private static final ThreadLocal<String> correlationIdThreadLocal = new ThreadLocal<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String correlationId = UUID.randomUUID().toString();
            correlationIdThreadLocal.set(correlationId);
            filterChain.doFilter(request, response);
        } finally {
            correlationIdThreadLocal.remove();
        }
    }

    public static String getCorrelationId() {
        return correlationIdThreadLocal.get();
    }
}
