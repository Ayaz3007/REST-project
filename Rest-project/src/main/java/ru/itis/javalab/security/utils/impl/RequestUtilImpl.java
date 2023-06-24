package ru.itis.javalab.security.utils.impl;

import org.springframework.stereotype.Component;
import ru.itis.javalab.security.utils.AuthorizationHeaderUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestUtilImpl implements AuthorizationHeaderUtil {

    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    private static final String BEARER = "Bearer ";

    @Override
    public boolean hasAuthorizationToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER_NAME);
        return header != null && header.startsWith(BEARER);
    }

    @Override
    public String getToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER_NAME);
        return header.substring(BEARER.length());
    }
}
