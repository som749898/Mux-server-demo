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
//      entityManagerFactoryRef = "orderEntityManagerFactory",
//      transactionManagerRef = "orderTransactionManager",
//      basePackages = "com.doubleDB.project.repository2")
//public class SecondDB {
//	@Bean(name="orderProperties")
//  @ConfigurationProperties("spring.seconddatasource")
//  public DataSourceProperties dataSourceProperties() {
//      return new DataSourceProperties();
//  }
//
//  @Bean(name="orderDatasource")
//  @ConfigurationProperties("spring.seconddatasource")
//  public DataSource datasource(@Qualifier("orderProperties") DataSourceProperties properties){
//      return properties.initializeDataSourceBuilder().build();
//  }
//  
//  @Bean(name="orderEntityManagerFactory")
//  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
//          ( @Qualifier("orderDatasource") DataSource dataSource){
//
//  	
//	  LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//		Properties jpaProperties = new Properties();
//      jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//      
//      entityManagerFactory.setJpaProperties(jpaProperties);
//      entityManagerFactory.setDataSource(dataSource);
//      entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//      entityManagerFactory.setPackagesToScan("com.doubleDB.project.entity");
//      return entityManagerFactory;
//  }
//  
//  @Bean(name = "orderTransactionManager")
//  @ConfigurationProperties("spring.jpa")
//  public PlatformTransactionManager transactionManager(
//          @Qualifier("orderEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//
//      return new JpaTransactionManager(entityManagerFactory);
//  }
//}
