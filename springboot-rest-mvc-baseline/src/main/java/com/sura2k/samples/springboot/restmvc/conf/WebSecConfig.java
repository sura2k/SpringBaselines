package com.sura2k.samples.springboot.restmvc.conf;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecConfig extends WebSecurityConfigurerAdapter {
	
	public WebSecConfig(){
		System.out.println("---WebSecConfig");
	}
	
	@Autowired
	private DataSource dataSource;

	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/login", "/logout").permitAll()
				.antMatchers("/unauthorized").permitAll()
				.anyRequest().authenticated()
			.and()
			.formLogin()
			.and().csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(passwordEncoder())
		.usersByUsernameQuery("select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Configuration
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {
    		http
    		.antMatcher("/rest/**")
			.authorizeRequests()
				.antMatchers("/rest/admin/**").hasRole("ADMIN")
				.antMatchers("/rest/manager/**").hasRole("MANAGER")
				.antMatchers("/rest/user/**").hasRole("USER")
				.anyRequest().authenticated()
			.and()
			.httpBasic();
        }
    }
}
