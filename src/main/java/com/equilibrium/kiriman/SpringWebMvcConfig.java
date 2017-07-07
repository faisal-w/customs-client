package com.equilibrium.kiriman;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class SpringWebMvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		if (!registry.hasMappingForPattern("/resources/**")) {
			registry
				.addResourceHandler("/resources/**")
				.addResourceLocations("classpath:@/resources/");
		}
		if (!registry.hasMappingForPattern("/static/**")) {
			registry
				.addResourceHandler("/static/**")
				.addResourceLocations("classpath:/static/");
		}
		if (!registry.hasMappingForPattern("/pdf/**")){
			registry
				.addResourceHandler("/pdf/**")
				.addResourceLocations("file:/root/respons_pdf/");
		}
	}
}
