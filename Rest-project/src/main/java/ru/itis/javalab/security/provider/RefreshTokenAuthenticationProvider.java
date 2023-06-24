package ru.itis.javalab.security.provider;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.itis.javalab.security.authentication.RefreshTokenAuthentication;
import ru.itis.javalab.security.exceptions.RefreshTokenException;
import ru.itis.javalab.security.utils.JwtUtil;
import ru.itis.javalab.services.JwtBlacklistService;

@Component
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenAuthenticationProvider implements AuthenticationProvider {

    private final JwtUtil jwtUtil;
    private final JwtBlacklistService jwtBlacklistService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String refreshTokenValue = (String) authentication.getCredentials();

        if (jwtBlacklistService.isExists(refreshTokenValue)) {
            throw new RefreshTokenException("Token was revoked");
        }

        try {
            return jwtUtil.buildAuthentication(refreshTokenValue);
        } catch (JWTVerificationException e) {
            log.info(e.getMessage());
            throw new RefreshTokenException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return RefreshTokenAuthentication.class.isAssignableFrom(authentication);
    }
}
