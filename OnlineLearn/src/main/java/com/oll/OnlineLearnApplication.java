package com.oll;


import com.oll.filter.PermissionFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineLearnApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean loginFilter(){
		FilterRegistrationBean registration = new FilterRegistrationBean(new PermissionFilter());
		registration.addUrlPatterns("/*");
		return registration;
	}

}
