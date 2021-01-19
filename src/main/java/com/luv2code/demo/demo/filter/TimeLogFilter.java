package com.luv2code.demo.demo.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "TimeLogFilter",urlPatterns = "/api/*")
@Order(1)
public class TimeLogFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(TimeLogFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Long beforeTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        logger.info("Time needed: " + (System.currentTimeMillis()- beforeTime));
    }
}
