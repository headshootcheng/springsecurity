package com.luv2code.demo.demo.modals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luv2code.demo.demo.modals.responses.AuthMessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessHandler implements AccessDeniedHandler {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        logger.error("AccessDenied error: {}", e.getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        AuthMessageResponse authMessageResponse = new AuthMessageResponse(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied!!!", System.currentTimeMillis(),true);
        httpServletResponse.getOutputStream().println(objectMapper.writeValueAsString(authMessageResponse));
    }
}
