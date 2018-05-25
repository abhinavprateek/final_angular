package com.bookstore.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.bookstore.service.UserSecurityService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	private BCryptPasswordEncoder passwordEncoder() {
		return SecurityUtility.passwordEncoder();
	}

	private static final String[] PUBLIC_MATCHERS = {
			"/css/**",
			"/js/**",
			"/image/**",
			/*"/book/**",*/
			"/user/**"
	};


	private static final String[] ADMIN_MATCHERS = {
			"/book/add",
			"/book/add/image",
			"/book/update",
			"/book/remove"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(ADMIN_MATCHERS).access("hasRole('ROLE_ADMIN')").and()
		.csrf().disable().cors().disable().httpBasic().and().authorizeRequests()
		.antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
		
		
		/*
		http.csrf().disable().cors().disable().httpBasic().and().authorizeRequests()
		.antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
		*/
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}

	/*
	@Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
	
	@Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        // no limit
        //resolver.setMaxUploadSize(-1);
        return resolver;
    }	
	*/
	
	
}
