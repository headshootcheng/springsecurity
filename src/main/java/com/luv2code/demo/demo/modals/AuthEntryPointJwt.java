package com.luv2code.demo.demo.modals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luv2code.demo.demo.exceptions.AuthorizationErrorException;
import com.luv2code.demo.demo.modals.responses.AuthMessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        AuthMessageResponse authMessageResponse = new AuthMessageResponse(HttpServletResponse.SC_UNAUTHORIZED, "Failed to authorize", System.currentTimeMillis(), true);
        response.getOutputStream().println(objectMapper.writeValueAsString(authMessageResponse));
//        throw new AuthorizationErrorException(authException.getMessage());
    }

}
