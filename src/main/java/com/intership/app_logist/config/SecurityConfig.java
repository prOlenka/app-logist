package com.intership.app_logist.config;

import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/dwh/v1/**").hasRole("LOGIST")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("logist").password("{noop}password").roles("LOGIST");
    }
}
