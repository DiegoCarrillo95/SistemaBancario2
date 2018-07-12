package com.diego.sistema_bancario;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.diego.models", "com.diego.facade", "com.diego.services",
		"com.diego.sistema_bancario", "com.diego.jpa" })
//@PropertySource(value = { "classpath:database.properties" })
public class AppConfig {

	// Referência:
	// http://www.technicalkeeda.com/spring-tutorials/spring-4-jdbctemplate-annotation-example

//	@Autowired
//	private Environment env;
//
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driver"));
//		dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
//		dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
//		dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
//		return dataSource;
//	}
//
//	@Bean
//	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		jdbcTemplate.setResultsMapCaseInsensitive(true);
//		return jdbcTemplate;
//	}

}
