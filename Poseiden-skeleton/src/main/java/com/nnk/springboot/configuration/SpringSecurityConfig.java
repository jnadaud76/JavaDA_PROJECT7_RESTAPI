package com.nnk.springboot.configuration;

import com.nnk.springboot.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.headers().frameOptions().disable();
        http
                .authorizeRequests()
                .antMatchers( "/static.css/**", "/", "/h2-console/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/user/*").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/loginsuccess", true)
                 .and()
                .oauth2Login()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/loginsuccess", true)
                .and()
                .logout()
                .logoutUrl("/app-logout")
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");



    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/css/**"
        );
    }
}


