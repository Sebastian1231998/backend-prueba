package com.jiksoft.library.configuration;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories(
        entityManagerFactoryRef = "libraryEntityManagerFactory",
        transactionManagerRef = "libraryTransactionManager",
        basePackages = {"com.jiksoft.library.repositories.jpa"}
)
@EntityScan(basePackages = {"com.jiksoft.library.models"})
public class LibraryDataSourceConfiguration {

    @Primary
    @Bean(name = "libraryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource libraryDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    @Primary
    @Bean(name = "libraryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean libraryEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("libraryDataSource") DataSource dataSource) {

        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        return builder
                .dataSource(dataSource)
                .packages("com.jiksoft.library.models")
                .persistenceUnit("libraryPersistenceUnit")
                .properties(jpaProperties)
                .build();
    }

    @Primary
    @Bean(name = "libraryTransactionManager")
    public PlatformTransactionManager libraryTransactionManager(
            @Qualifier("libraryEntityManagerFactory") EntityManagerFactory libraryEntityManagerFactory) {
        return new JpaTransactionManager(libraryEntityManagerFactory);
    }
}
