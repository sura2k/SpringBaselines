package com.sura2k.examples.springsec.spring.security;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(AuthenticationManagerBuilder auth)
			throws Exception {

		// In-Memory authentication
		// auth.inMemoryAuthentication().withUser("testuser").password("password123").roles("ROLE_USER");

		// Using custom UserDetailsService DAO
		// auth.userDetailsService(new AppUserDetailsServiceDAO());

		// using JDBC
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx
				.lookup("java:/comp/env/jdbc/demodb");

		final String findUserSql = 
				"select username, password, enabled " + 
				"from users " + 
				"where username = ?";
		
		final String findRolesSql = 
				"select username, role " + 
				"from roles " + 
				"where username = ?";
		
		auth.jdbcAuthentication().dataSource(ds)
				.usersByUsernameQuery(findUserSql)
				.authoritiesByUsernameQuery(findRolesSql);
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .authorizeRequests()
                .antMatchers("/login", "/logout", "/reg").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
            	.loginPage("/login")
            	.defaultSuccessUrl("/home")
            	.and()
            .logout()
            	.invalidateHttpSession(true)
            	.logoutUrl("/logout")
            	.logoutSuccessUrl("/login");
        // @formatter:on
    }

}
