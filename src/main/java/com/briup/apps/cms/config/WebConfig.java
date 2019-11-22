package com.briup.apps.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Bean
	public JwtInterceptor jwtInterceptor() {
		return new JwtInterceptor();
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//拦截路径可自行配置多个 可用 ，分隔开
		registry.addInterceptor(jwtInterceptor())
//				.addPathPatterns("/category/**","/article/**","/user/**","/role/**","/privilege/**")
				.addPathPatterns("/**")
				.excludePathPatterns(
						"/swagger-resources/**","/v2/**","/swagger-ui.html","/webjars/**",
						"/user/login","/user/logout","/user/info");
	}
	
	
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
//设置白名单
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET","POST","PUT","OPTIONS","DELETE","PATCH")
				.allowedHeaders("*")
				.allowCredentials(true)
				.maxAge(3600);
	}

}
