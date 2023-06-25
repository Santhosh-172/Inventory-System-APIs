package com.springboot.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.main.service.MyUserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyUserService myUserService;
	 @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// here I am going to build my own AuthManager that will read user details from the DB
		/* 
		 auth.inMemoryAuthentication()
		 .withUser("msdhoni@incedo.com").password(getEncoder().encode("csk")).authorities("ADMIN")
		 .and()
		 .withUser("vkohli@incedo.com").password(getEncoder().encode("rcb")).authorities("USER");
		 */
		 //point spring auth to DB 
		 auth.authenticationProvider(getProvider());
	} 
	 private AuthenticationProvider getProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(getEncoder());
		//to point to DB, go to service first. 
		dao.setUserDetailsService(myUserService);
		return dao;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// here, I am going to list out my api's along with User permissions
		 http.authorizeRequests()
		 .antMatchers(HttpMethod.GET,"/user/login").authenticated()
		 .antMatchers(HttpMethod.POST, "/inwardregister/add/{productId}/{godownId}/{supplierId}").hasAuthority("MANAGER")
		 .antMatchers(HttpMethod.POST, "/customer/add").permitAll()
		 .antMatchers(HttpMethod.POST, "/product/add").hasAnyAuthority("MANAGER","USER")
		 .antMatchers(HttpMethod.POST, "/supplier/add").authenticated()
		 .antMatchers(HttpMethod.POST, "/admin/add").permitAll()
		 .antMatchers(HttpMethod.POST, "/manager/add").hasAuthority("ADMIN")
		 .antMatchers(HttpMethod.POST, "/employee/add/{managerId}").hasAuthority("ADMIN")
		 .antMatchers(HttpMethod.GET, "/inwardregister/report").hasAuthority("ADMIN")
		 .anyRequest().permitAll()
		 .and()
		 .httpBasic()
		 .and()
		 .csrf().disable();
	}
	 
	 @Bean
	 public PasswordEncoder getEncoder() {
		 return new BCryptPasswordEncoder();
	 }
}















