package com.niit.ZShopBackend.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.niit.ZShopBackend")
@EnableTransactionManagement
public class HibernateConfig {
	
	private final String Database_Driver = "org.h2.Driver";
	private final String Database_Url = "jdbc:h2:tcp://localhost/~/shop";
	private final String Database_UserName = "sa";
	private final String Database_Password = "";
	private final String Database_Dialect = "org.hibernate.dialect.H2Dialect";
	
	@Bean("dataSource")
	public DataSource getDataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(Database_Driver);
		dataSource.setUrl(Database_Url);
		dataSource.setUsername(Database_UserName);
		dataSource.setPassword(Database_Password);
		
		System.out.println("------------------------------DataSource Created----------------------");
		return dataSource;
	}
	@Bean("sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder lsfb = new LocalSessionFactoryBuilder(dataSource);
		lsfb.addProperties(getHibernateProperties());
		lsfb.scanPackages("com.niit.ZShopBackend.model");
		
		System.out.println("--------------------------SessionFactory Created----------------------");
		return lsfb.buildSessionFactory();
	}
	private Properties getHibernateProperties()
	{
		Properties prop = new Properties();
		prop.put("hibernate.dialect", Database_Dialect);
		prop.put("hibernate.hbm2ddl.auto", "update");
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.format_sql", "true");
		
		System.out.println("----------------------Hibernate Properties Created-------------------------------");
		return prop;
	}
	@Bean
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("-------------------------TransactionManager Created--------------------");
		return new HibernateTransactionManager(sessionFactory);
	}

}
