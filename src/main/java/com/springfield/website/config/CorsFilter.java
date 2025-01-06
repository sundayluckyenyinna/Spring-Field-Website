package com.springfield.website.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        if (httpRequest.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setStatus(Response.SC_OK);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, HEAD, OPTIONS, DELETE, PATCH");
            response.setHeader("Access-Control-Allow-Headers", "Channel-ID, Channel-Secret, Content-Type, Accept");
            return;
        }else{
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, HEAD, OPTIONS, DELETE, PATCH");
            response.setHeader("Access-Control-Allow-Headers", "Channel-ID, Channel-Secret, Content-Type, Accept");
        }
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
