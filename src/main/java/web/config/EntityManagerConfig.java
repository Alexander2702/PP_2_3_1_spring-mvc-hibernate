package web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;
import javax.sql.DataSource;



@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@PropertySource(value = {"classpath:application.properties"})
@ComponentScan("web")
public class EntityManagerConfig {

    private final Environment environment;

    public EntityManagerConfig(Environment environment) {
        this.environment = environment;
    }


    /*Это основная часть конфигурации — и она выполняется с помощью фабричного компонента Spring
    — более гибкого LocalContainerEntityManagerFactoryBean,создается
    EntityManagerFactory со стандартным контрактом загрузки контейнера JPA. Используя его, мы можем настроить общий JPA  EntityManagerFactory
    в контексте Spring приложения.*/
    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setJpaVendorAdapter(getJpaVendorAdapter());
        em.setDataSource(dataSource());
        em.setPackagesToScan("web");
        em.setJpaProperties(hibernateProperties());
        return em;
    }
    // указываем поставщика JPA(Hibernate)
    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }
    //предоставляет основные методы для управления операциями транзакций во время выполнения: начало, фиксация и откат.
    @Bean
    public PlatformTransactionManager txManager() {
        return new JpaTransactionManager(
                getEntityManagerFactory().getObject());
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
        dataSource.setUrl(environment.getRequiredProperty("db.url"));
        dataSource.setUsername(environment.getRequiredProperty("db.username"));
        dataSource.setPassword(environment.getRequiredProperty("db.password"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
