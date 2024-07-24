package com.eshop.userservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // allow all request to pass without any authentication

        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                    requests -> requests
                            .requestMatchers(HttpMethod.GET, "/users").permitAll()
                            .requestMatchers(HttpMethod.POST, "/users/login") .permitAll()
                            .requestMatchers(HttpMethod.POST,"/users/signup").permitAll()
                            .requestMatchers(HttpMethod.POST, "users/validate/{token}").permitAll()
                            .anyRequest()
                            .authenticated()

                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}


//@Override
//protected void configure(HttpSecurity http) throws Exception {
//    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
//            authorizeRequests().antMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMIN", "USER")
//            .antMatchers(HttpMethod.POST, "/routeA/**").hasAnyRole("ADMIN", "USER")
//            .antMatchers(HttpMethod.POST, "/routeB/**").hasRole("ADMIN")
//            .antMatchers(HttpMethod.DELETE, "/routeB/**").hasRole("ADMIN").and().
//            requestCache().requestCache(new NullRequestCache()).and().
//            httpBasic().authenticationEntryPoint(authenticationEntryPoint).and().
//            cors().and().
//            csrf().disable();
//}