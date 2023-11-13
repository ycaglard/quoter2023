package com.quoter.quoter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import com.quoter.quoter.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userDetailsService;

    public SecurityConfig(UserService userService) {
        this.userDetailsService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                .requestMatchers(h2ConsoleMatcher()).permitAll()
                .anyRequest().authenticated())
                .csrf().ignoringRequestMatchers(h2ConsoleMatcher())
                .and()
                .headers().frameOptions().disable(); // Allow displaying H2 console in an iframe
    }

    @Bean
    public RequestMatcher h2ConsoleMatcher() {
        return request -> request.getServletPath().startsWith("/h2-console");
    }
}
