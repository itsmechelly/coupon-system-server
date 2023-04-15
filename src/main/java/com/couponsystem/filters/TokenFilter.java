package com.couponsystem.filters;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import com.couponsystem.security.JwtUtil;

public class TokenFilter implements Filter {
    private JwtUtil jwtUtil;

    public TokenFilter(JwtUtil jwtUtil) {
        super();
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String method = req.getMethod();
        String token = req.getHeader("token");
        String acrh = req.getHeader("access-control-request-headers");
        String url = req.getRequestURI();

        if (url.contains("/login") || url.contains("/pics")) {
            System.out.println("LOGIN FILTER PASS-------------");
            chain.doFilter(request, response);
            return;
        }

        if (token != null) {
            if (jwtUtil.isTokenExpired(token)) {
                res.sendError(HttpStatus.UNAUTHORIZED.value(), "you are not authorized");
            }
            if (req.getRequestURI().contains("/admin")) {
                if (jwtUtil.extractUserTypeForFilter(token) == 0) {
                    System.out.println("ADMIN FILTER PASS-------------");
                    chain.doFilter(request, response);
                } else {
                    System.out.println("ADMIN FILTER FAIL-------------");
                    res.sendError(HttpStatus.UNAUTHORIZED.value(), "you are not an admin");
                }

            } else if (req.getRequestURI().contains("/company")) {
                if (jwtUtil.extractUserTypeForFilter(token) == 1) {
                    System.out.println("COMPANY FILTER PASS-------------");
                    chain.doFilter(request, response);
                } else {
                    System.out.println("COMPANY FILTER FAIL-------------");
                    res.sendError(HttpStatus.UNAUTHORIZED.value(), "you are not a company");
                }
            } else if (req.getRequestURI().contains("/customer")) {
                if (jwtUtil.extractUserTypeForFilter(token) == 2) {
                    System.out.println("CUSTOMER FILTER PASS-------------");
                    chain.doFilter(request, response);
                } else {
                    System.out.println("CUSTOMER FILTER FAIL-------------");
                    res.sendError(HttpStatus.UNAUTHORIZED.value(), "you are not a customer");
                }
            } else {
                System.out.println("LOGIN FILTER PASS-------------");
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
            if (acrh != null && method.equals("OPTIONS")) {
                System.out.println("PREFLIGHT-------------");
                res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
                res.setHeader("Access-Control-Allow-Origin", "*");
                res.setHeader("Access-Control-Allow-Headers", "*");
                res.sendError(HttpStatus.OK.value(), "preflight");
            } else {
                chain.doFilter(request, response);
                System.out.println("LOGIN FILTER FAILL-------------");
                res.sendError(HttpStatus.UNAUTHORIZED.value(), "you are not logged in");
            }
        }
    }
}
