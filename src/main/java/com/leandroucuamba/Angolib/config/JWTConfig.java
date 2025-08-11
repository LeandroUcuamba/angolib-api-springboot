package com.leandroucuamba.Angolib.config;

import com.leandroucuamba.Angolib.filter.FiltroDeTokens;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTConfig {

    @Bean
    public FilterRegistrationBean<FiltroDeTokens> filtroDasRotas(){
        FilterRegistrationBean<FiltroDeTokens> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new FiltroDeTokens());
        filterRegistrationBean.addUrlPatterns("/auth/usuarios/*");
        return filterRegistrationBean;
    }
}
