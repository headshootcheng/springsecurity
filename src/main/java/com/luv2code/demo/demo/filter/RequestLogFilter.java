package com.luv2code.demo.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter(filterName = "RequestLogFilter", urlPatterns = "/api/*")
@Order(2)
public class RequestLogFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(RequestLogFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        logger.info("request: " + request);
        filterChain.doFilter(servletRequest,servletResponse);
        logger.info("response: " + servletResponse);
    }
}
