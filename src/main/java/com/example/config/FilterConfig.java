package com.example.config;

import com.example.web.filters.FilterLogin;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<FilterLogin> loggingFilter(FilterLogin filterLogin) {
        FilterRegistrationBean<FilterLogin> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filterLogin);
        registrationBean.addUrlPatterns("/auth.jhtml"); // Фильтр сработает только на этом URL
        registrationBean.setOrder(1); // Приоритет (чем меньше число, тем выше приоритет)
        return registrationBean;
    }
}
