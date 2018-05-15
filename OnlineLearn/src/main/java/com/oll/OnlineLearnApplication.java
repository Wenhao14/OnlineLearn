package com.oll;


import com.oll.filter.FlushSessionFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class OnlineLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineLearnApplication.class, args);
	}

	/**
	 * 登录过滤
	 * @return
	 */
	@Bean
	public FilterRegistrationBean flushSessionFilter(){
		FilterRegistrationBean registration = new FilterRegistrationBean(new FlushSessionFilter());
		registration.addUrlPatterns("/*");
		return registration;
	}

	/**
	 * 错误请求处理
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(){
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND,"/page/error/404.html");
				ErrorPage tooManyResult = new ErrorPage(HttpStatus.TOO_MANY_REQUESTS,"/page/error/error.html");
				ErrorPage error500 = new ErrorPage(HttpStatus.BAD_REQUEST,"/page/error/error.html");
				configurableEmbeddedServletContainer.addErrorPages(error404Page);
				configurableEmbeddedServletContainer.addErrorPages(tooManyResult);
				configurableEmbeddedServletContainer.addErrorPages(error500);
			}
		};
	}
}
