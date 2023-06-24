package ru.itis.javalab.security.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.javalab.security.utils.AuthorizationHeaderUtil;
import ru.itis.javalab.services.JwtBlacklistService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRevokeFilter extends OncePerRequestFilter {
    private final JwtBlacklistService jwtBlacklistService;
    private final AuthorizationHeaderUtil authorizationHeaderUtil;
    public static final String REVOKE_TOKEN_URL = "/auth/token/revoke";

    private final AntPathRequestMatcher revokeMatcher = new AntPathRequestMatcher(REVOKE_TOKEN_URL, "POST");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (revokeMatcher.matches(request)) {
            if (authorizationHeaderUtil.hasAuthorizationToken(request)) {
                jwtBlacklistService.add(authorizationHeaderUtil.getToken(request));
                response.setStatus(HttpStatus.OK.value());
            } else {
                response.setStatus(HttpStatus.FORBIDDEN.value());
            }
        }
        else {
            filterChain.doFilter(request, response);
        }
    }
}
