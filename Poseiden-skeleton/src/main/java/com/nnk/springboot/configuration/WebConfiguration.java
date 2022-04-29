package com.nnk.springboot.configuration;


import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;

@Configuration
    public class WebConfiguration {
        @Bean
        ServletRegistrationBean<? extends Servlet> h2servletRegistration(){
            ServletRegistrationBean<? extends Servlet> registrationBean = new ServletRegistrationBean(new WebServlet());
            registrationBean.addUrlMappings("/h2-console/*");
            return registrationBean;
        }
}
