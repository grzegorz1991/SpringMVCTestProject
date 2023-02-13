package th.grzegorzNauka.contact.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
@PropertySource("classpath:config.properties")
@EnableWebMvc
public class SpringMvcConfig extends WebMvcConfigurerAdapter{

    @Autowired
    private Environment env;

    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    */
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        String driverClassName = env.getProperty("driverClassName");
        String url = env.getProperty("url");
        String root = env.getProperty("root");
        String password = env.getProperty("password");

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(root);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public ContactDAO getContactDAO() {
        return new ContactDAOImpl(getDataSource());
    }
}