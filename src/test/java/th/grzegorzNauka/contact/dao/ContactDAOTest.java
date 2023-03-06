package th.grzegorzNauka.contact.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import th.grzegorzNauka.contact.config.SpringMvcConfig;
import th.grzegorzNauka.contact.model.Contact;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMvcConfig.class) // ,loader = AnnotationConfigContextLoader.class)
@TestPropertySource(value ="classpath:postgreSQLconfig.properties")
class ContactDAOTest {
    @Autowired
    private DriverManagerDataSource dataSource;
    @Autowired
    private ContactDAO dao;

    @Value("${string.message}")
    String message;
    @BeforeEach
    void beforeEach(){
        //Creating new context
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("postgreSQLConnectionContext.xml");
        //retrieving bean from context
        DriverManagerDataSource dataSource = context.getBean("postgreSQLContext", DriverManagerDataSource.class);
        context.close();

        dao = new ContactDAOImpl(dataSource);
    }
    Integer testId(){
        return dao.list().get(dao.list().size()-1).getId();
    }

    @org.junit.jupiter.api.Test
    void save() {

        Contact contact = new Contact("Steve Jobs2", "steve@jobs.com", "California", "1234567890");
        int result = dao.save(contact);
        assertTrue(result > 0);
    }

    @org.junit.jupiter.api.Test
    void update() {
        Contact contact = new Contact(testId(),"Test MCKenny", "still@jobs.com", "Texas", "12345655590");
        int result = dao.update(contact);
        assertTrue(result > 0);
    }

    @org.junit.jupiter.api.Test
    void get() {
        Integer id = testId();
        Contact contact = dao.get(id);
        if(contact != null){
            System.out.println(contact);
        }
        assertNotNull(contact);
    }

    @org.junit.jupiter.api.Test
    void delete() {
        Integer id = testId();
        int result = dao.delete(id);
        assertTrue(result > 0);
    }

    @org.junit.jupiter.api.Test
    void list() {
        List<Contact> listContact = dao.list();

        for(Contact c: listContact){
            System.out.println(c);
        }

        assertNotNull(listContact);

    }
}