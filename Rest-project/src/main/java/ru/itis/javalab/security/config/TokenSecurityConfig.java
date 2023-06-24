package ru.itis.javalab.security.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import ru.itis.javalab.security.details.UserDetailsServiceImpl;
import ru.itis.javalab.security.filters.JwtAuthenticationFilter;
import ru.itis.javalab.security.filters.JwtAuthorizationFilter;
import ru.itis.javalab.security.filters.JwtLogoutFilter;
import ru.itis.javalab.security.filters.JwtRevokeFilter;
import ru.itis.javalab.security.provider.RefreshTokenAuthenticationProvider;

@EnableWebSecurity
@RequiredArgsConstructor
public class TokenSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    private final AuthenticationProvider provider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security,
                                                   JwtAuthorizationFilter jwtAuthorizationFilter,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter,
                                                   JwtLogoutFilter logoutFilter,
                                                   JwtRevokeFilter jwtRevokeFilter) throws Exception {
        security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        security.csrf().disable();

        security.authorizeRequests()
                .antMatchers("/swagger-ui.html/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/users/**").permitAll()
                .antMatchers("/users/**").authenticated()
                .antMatchers(HttpMethod.GET, "/ads/**").permitAll()
                .antMatchers("/ads/**").authenticated()
                .antMatchers("/cheques/**").authenticated();

        security.addFilter(jwtAuthenticationFilter)
                .addFilterAt(logoutFilter, LogoutFilter.class);
        security.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtRevokeFilter, JwtAuthenticationFilter.class);

        return security.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/resources/static/**");
    }

    @Autowired
    public void bindUserDetailsServiceAndPasswordEncoder(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
        builder.authenticationProvider(provider);
    }
}
