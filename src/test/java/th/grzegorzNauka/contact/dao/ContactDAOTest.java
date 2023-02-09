package th.grzegorzNauka.contact.dao;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import th.grzegorzNauka.contact.model.Contact;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactDAOTest {

    private DriverManagerDataSource dataSource;
    private ContactDAO dao;

    @BeforeEach
    void beforeEach(){
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/contactdb?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("6SIC8lyskYSNClWe0bXi");

        dao = new ContactDAOImpl(dataSource);
    }


    @org.junit.jupiter.api.Test
    void save() {

        Contact contact = new Contact("Steve Jobs2", "steve@jobs.com", "California", "1234567890");
        int result = dao.save(contact);
        assertTrue(result > 0);
    }

    @org.junit.jupiter.api.Test
    void update() {
        Contact contact = new Contact(6,"John MCKenny", "still@jobs.com", "Texas", "12345655590");
        int result = dao.update(contact);
        assertTrue(result > 0);
    }

    @org.junit.jupiter.api.Test
    void get() {
        Integer id = 1;
        Contact contact = dao.get(id);
        if(contact != null){
            System.out.println(contact);
        }
        assertNotNull(contact);
    }

    @org.junit.jupiter.api.Test
    void delete() {
        Integer id = 11;
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