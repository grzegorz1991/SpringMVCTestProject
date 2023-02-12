package th.grzegorzNauka.contact.config;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Registration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        //appContext.register(SpringMvcConfig.class);
        appContext.setConfigLocation("th.grzegorzNauka.contact.config");
        ServletRegistration.Dynamic dispatcher
                = servletContext.addServlet("SpringDispatcher", new DispatcherServlet(appContext));
       //below was not in tutorial but is in another
        servletContext.addListener(new ContextLoaderListener(appContext));

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
