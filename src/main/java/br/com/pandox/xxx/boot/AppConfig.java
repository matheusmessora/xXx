package br.com.pandox.xxx.boot;

import br.com.pandox.xxx.boot.util.BasePropertyConfigurer;
import br.com.pandox.xxx.boot.util.NameGenerator;
import org.apache.log4j.Logger;
import org.hibernate.ejb.HibernatePersistence;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Properties;

/**
 * Classe de configuração Spring MVC 3
 */
@Configuration
@PropertySource("classpath:config.properties")
@ComponentScan(nameGenerator = NameGenerator.class, basePackages = "br.com.pandox.xxx", excludeFilters = @Filter(Configuration.class))
@EnableTransactionManagement
@EnableJpaRepositories("br.com.pandox.xxx.repo")
@EnableWebMvc
@EnableAsync
@EnableScheduling
public class AppConfig extends WebMvcConfigurerAdapter {
    private static Logger log = Logger.getLogger(AppConfig.class);


    @Autowired
    private Environment env;

//    @Bean
//    public static MultipartResolver multipartResolver() {
//        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
////        commonsMultipartResolver.setMaxUploadSize(250000);
//        return commonsMultipartResolver;
//    }

    @Bean
    public static final BasePropertyConfigurer propertyConfigurer() {
        BasePropertyConfigurer config = new BasePropertyConfigurer();
        config.setIgnoreResourceNotFound(false);
        Resource location = new ClassPathResource("config.properties");
        config.setLocation(location);
        return config;
    }

//    @Bean
//    public AWSCredentials getAWSCredentials() {
//
//        String accessKey = env.getProperty("aws_access_key_id");
//        String secretKey = env.getProperty("aws_secret_access_key");
//        return new BasicAWSCredentials(accessKey, secretKey);
//    }

    @Bean
    public DriverManagerDataSource dataSource() {
        log.info("Configurando [dataSource]...");
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.hsqldb.jdbcDriver");

        ds.setUrl(env.getProperty("jdbc.url"));
        ds.setUsername(env.getProperty("jdbc.user"));
        ds.setPassword(env.getProperty("jdbc.pass"));
        log.info("Datasource configurado: " + ds.toString());
        return ds;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        log.info("Configurando [entityManagerFactory]...");
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("br.com.pandox.xxx");
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);

        Properties hibernateProperties = new Properties();

        hibernateProperties.put("hibernate.hbm2ddl.auto", "create");

        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("hibernate.show_sql", "true");

        entityManagerFactoryBean.setJpaProperties(hibernateProperties);

        log.info(entityManagerFactoryBean);
        return entityManagerFactoryBean;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public EmbeddedCacheManager cache() {
        return new DefaultCacheManager();
    }


//    @Bean
//    protected ServletContextTemplateResolver getTemplateResolver() {
//
//        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
//                templateResolver.setPrefix("/WEB-INF/templates/");
//        templateResolver.setSuffix(".xhtml");
//        templateResolver.setTemplateMode("XHTML");
//        return templateResolver;
//    }
//
//    @Bean
//    protected SpringTemplateEngine getSpringTemplateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(getTemplateResolver());
//        return templateEngine;
//    }
//
//    @Bean
//    protected ViewResolver getViewResolver() {
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(getSpringTemplateEngine());
//        viewResolver.setCharacterEncoding("UTF-8");
//        viewResolver.setContentType("text/html; charset=UTF-8");
//        return viewResolver;
//    }

}
