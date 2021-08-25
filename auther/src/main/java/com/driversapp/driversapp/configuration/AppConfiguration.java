package com.driversapp.driversapp.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.driversapp.driversapp.view.APIResponseView;

@Configuration
public class AppConfiguration {

	@Value("${spring.datasource.username}")
	private String databaseUserName;

	@Value("${spring.datasource.password}")
	private String databasePassword;

	@Bean(name = "ApiResponse")
	public APIResponseView createApiResponseViewBean() {
		APIResponseView apiResponseView = new APIResponseView();
		return apiResponseView;
	}

	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url("jdbc:mysql://localhost:3306/vitasoft");
		dataSourceBuilder.username(databaseUserName);
		dataSourceBuilder.password(databasePassword);
		return dataSourceBuilder.build();
	}
}
