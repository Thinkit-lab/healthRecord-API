package com.olukunle.mkobo_assessment.security;

import com.olukunle.mkobo_assessment.service.StaffService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authorizationFilterBean(AuthorizationFilter authorizationFilter) {
        FilterRegistrationBean<AuthorizationFilter> authFilterBean = new FilterRegistrationBean<>();
        authFilterBean.setFilter(authorizationFilter);
        authFilterBean.addUrlPatterns("/api/patient/*", "/api/staff/update");
        return authFilterBean;
    }

    @Bean
    public AuthorizationFilter authorizationFilter(StaffService staffService, UUIDEncryptor uuidEncryptor) {
        return new AuthorizationFilter(staffService, uuidEncryptor);
    }
}
