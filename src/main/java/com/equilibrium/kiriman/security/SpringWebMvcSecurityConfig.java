package com.equilibrium.kiriman.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by faisalw on 3/29/17.
 */
@Configuration
@EnableWebMvc
public class SpringWebMvcSecurityConfig extends WebMvcConfigurerAdapter{


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        if (!registry.hasMappingForPattern("/resources/**")){
            registry
                    .addResourceHandler("/resources/**")
                    .addResourceLocations("classpath:@/resources/");
        }
        if (!registry.hasMappingForPattern("/static/**")) {
            registry
                    .addResourceHandler("/static/**")
                    .addResourceLocations("classpath:/static/");
        }
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setFallbackPageable(new PageRequest(0,10));
        argumentResolvers.add(resolver);
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/403").setViewName("403_page");
    }

}
