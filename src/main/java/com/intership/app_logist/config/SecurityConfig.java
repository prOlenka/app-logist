//package com.intership.app_logist.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.client.oidc.authentication.OidcAuthorizationCodeReactiveAuthenticationManager;
//import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
//import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationCodeAuthenticationTokenConverter;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//@EnableReactiveMethodSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, ReactiveClientRegistrationRepository clientRegistrationRepository) {
//        http
//                .authorizeExchange(exchanges -> exchanges
//                        .pathMatchers("/api/logist/**").hasRole("LOGIST")
//                        .anyExchange().authenticated()
//                )
//                .oauth2Login(oauth2 -> oauth2
//                        .authenticationManager(new OidcAuthorizationCodeReactiveAuthenticationManager(clientRegistrationRepository))
//                        .authorizationEndpoint()
//                        .authorizationRequestResolver(new ServerOAuth2AuthorizationCodeAuthenticationTokenConverter(clientRegistrationRepository))
//                );
//
//        return http.build();
//    }
//}