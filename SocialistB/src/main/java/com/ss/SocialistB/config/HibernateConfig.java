package com.ss.SocialistB.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ss.SocialistB.model.Blog;
import com.ss.SocialistB.model.BlogComment;
import com.ss.SocialistB.model.Friend;
import com.ss.SocialistB.model.Job;

import com.ss.SocialistB.model.UploadFile;
import com.ss.SocialistB.model.User;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages="com.ss")
public class HibernateConfig {
	
	@Bean(name="dataSource")
	
	public DataSource getOracleDataSource(){
		DriverManagerDataSource driverManagerDataSource = 
				new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		
		driverManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		
		driverManagerDataSource.setUsername("Socialism");
		
		driverManagerDataSource.setPassword("941996");
		
		return driverManagerDataSource;		
	}
	
	public Properties getHibernateProperties() {
		Properties properties=new Properties();
		
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		
		properties.setProperty("hibernate.show_sql", "true");
		
    	properties.setProperty("hibernate.hbm2ddl.auto","update");
		
		return properties;
	}
	@Autowired
	@Bean
	public SessionFactory getSessionFactory()
	{
		LocalSessionFactoryBuilder localSessionFactoryBuilder= 
				new LocalSessionFactoryBuilder(getOracleDataSource());
	
		localSessionFactoryBuilder.addProperties(getHibernateProperties());
		
		localSessionFactoryBuilder.addAnnotatedClass(Blog.class);
		localSessionFactoryBuilder.addAnnotatedClass(BlogComment.class);

		localSessionFactoryBuilder.addAnnotatedClass(User.class);
		localSessionFactoryBuilder.addAnnotatedClass(Job.class);
		localSessionFactoryBuilder.addAnnotatedClass(Friend.class);
		localSessionFactoryBuilder.addAnnotatedClass(UploadFile.class);
		
		return localSessionFactoryBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager transactionManager=
				new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		
		System.out.println("Data Source Created");
		
		return transactionManager;
	}
	

}