package th.grzegorzNauka.contact.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import th.grzegorzNauka.contact.dao.ContactDAO;
import th.grzegorzNauka.contact.dao.ContactDAOImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages="th.grzegorzNauka.contact")

@EnableWebMvc
public class SpringMvcConfig extends WebMvcConfigurerAdapter{



    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    /*
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    */

    @Bean
    public DataSource getDataSource() {
        //Creating new context
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("postgreSQLConnectionContext.xml");
        //retrieving bean from context
        DriverManagerDataSource dataSource = context.getBean("postgreSQLContext", DriverManagerDataSource.class);
        /*
        String driverClassName = env.getProperty("spring.datasource.driver-class-name");
        String url = env.getProperty("spring.datasource.url");
        String username = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
*/
        return dataSource;
    }


    @Bean
    public ContactDAO getContactDAO() {
        return new ContactDAOImpl(getDataSource());
    }
}