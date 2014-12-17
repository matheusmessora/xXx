package br.com.pandox.xxx.boot;

import br.com.pandox.xxx.boot.util.NameGenerator;
import org.apache.log4j.Logger;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import java.util.ArrayList;

/**
 * Classe de configuração Spring MVC
 */
@Configuration
@ComponentScan(nameGenerator = NameGenerator.class, basePackages = "br.com.pandox.xxx", excludeFilters = @Filter(Configuration.class))
@EnableTransactionManagement
@EnableJpaRepositories("br.com.pandox.xxx.repo")
@EnableAsync
@EnableWebMvc
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


//    @Bean
//    public AWSCredentials getAWSCredentials() {
//
//        String accessKey = env.getProperty("aws_access_key_id");
//        String secretKey = env.getProperty("aws_secret_access_key");
//        return new BasicAWSCredentials(accessKey, secretKey);
//    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public EmbeddedCacheManager cache() {
        return new DefaultCacheManager();
    }

    @Bean
    protected TemplateResolver getTemplateResolver() {

        TemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/thymeleaf/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    protected SpringTemplateEngine getTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(getTemplateResolver());
        return templateEngine;
    }

    @Bean
    protected ContentNegotiatingViewResolver getViewResolver() {
        ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();

        ArrayList<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(getTemplateEngine());
        thymeleafViewResolver.setOrder(1);

        viewResolvers.add(thymeleafViewResolver);
        viewResolver.setViewResolvers(viewResolvers);
        return viewResolver;
    }

}
