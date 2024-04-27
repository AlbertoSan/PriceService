package com.capitole.app.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimitFilter implements Filter {

    private final long MAX_REQUESTS_PER_SECOND = 5; // 5 requests per second
    private final ConcurrentHashMap<String, AtomicInteger> clients = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String clientIp = request.getRemoteAddr();
        AtomicInteger rateInfo = clients.computeIfAbsent(clientIp, k -> new AtomicInteger(0));

        if (rateInfo.incrementAndGet() > MAX_REQUESTS_PER_SECOND) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            httpResponse.getWriter().write("Too many requests");
            return;
        }

        chain.doFilter(request, response);

        // Decrement rate info after a second. This is a simple way to reset the counter.
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }
            rateInfo.decrementAndGet();
        }).start();
    }
}

