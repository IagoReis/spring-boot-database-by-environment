package br.com.mundodev.dbbyenvironment.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("!local")
@ConditionalOnExpression("#{(systemEnvironment['mariadb']?:'false').equals('true')}")
@Configuration
public class MariaDbDataSourceConfig {
	
	@Bean
	@Primary
	@ConfigurationProperties("datasource.mariadb")
	public DataSourceProperties getDatasourceProperties() {
	    return new DataSourceProperties();
	}
	
	@Bean
    public DataSource getDataSource(final DataSourceProperties properties) {
		
        final var dataSourceBuilder = DataSourceBuilder.create();
        
        dataSourceBuilder.driverClassName(properties.getDriverClassName());
        dataSourceBuilder.url(properties.getUrl());
        dataSourceBuilder.username(properties.getUsername());
        dataSourceBuilder.password(properties.getPassword());
        
        return dataSourceBuilder.build();
    }

}
