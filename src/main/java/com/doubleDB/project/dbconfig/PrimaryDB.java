//package com.doubleDB.project.dbconfig;
//
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import jakarta.persistence.EntityManagerFactory;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "userEntityManagerFactory",
//        transactionManagerRef = "userTransactionManager",
//        basePackages = "com.doubleDB.project.repository")
//public class PrimaryDB {
//	// retieve information from application.properties
//		@Primary
//	    @Bean(name="userProperties")
//	    @ConfigurationProperties("spring.datasource")
//	    public DataSourceProperties dataSourceProperties() {
//	        return new DataSourceProperties();
//	    }
//		
//		// retieve properties from userproperties and build it to configure the data source
//		@Primary
//		@Bean(name="userDatasource")
//		@ConfigurationProperties(prefix = "spring.datasource")
//		public DataSource datasource(@Qualifier("userProperties") DataSourceProperties properties){
//
//		    return dataSourceProperties().initializeDataSourceBuilder().build();
//		}// it injects the data source inito application properties
//		
//		@Primary
//		@Bean(name="userEntityManagerFactory")
//		public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
//		        ( @Qualifier("userDatasource") DataSource dataSource){
//			LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//			Properties jpaProperties = new Properties();
//	        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//	        entityManagerFactory.setJpaProperties(jpaProperties);
//	        entityManagerFactory.setDataSource(dataSource);
//	        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//	        entityManagerFactory.setPackagesToScan("com.doubleDB.project.entity");
//	        return entityManagerFactory;
//		}
//		
//		@Primary
//		@Bean(name = "userTransactionManager")
//		@ConfigurationProperties("spring.jpa")
//		public PlatformTransactionManager transactionManager(
//		    @Qualifier("userEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//
//		    return new JpaTransactionManager(entityManagerFactory);
//		}
//
//
//}
