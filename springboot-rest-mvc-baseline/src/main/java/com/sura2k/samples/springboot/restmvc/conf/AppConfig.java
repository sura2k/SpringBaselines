package com.sura2k.samples.springboot.restmvc.conf;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({WebMvcConfig.class, WebSecConfig.class, WebServerConfig.class})
public class AppConfig {
	
	public AppConfig(){
		System.out.println("---APP CONFIG");
	}
	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/certmgmt");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
}
