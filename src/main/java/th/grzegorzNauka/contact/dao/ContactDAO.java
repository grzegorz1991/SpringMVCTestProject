package th.grzegorzNauka.contact.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import th.grzegorzNauka.contact.model.Contact;

import java.util.List;

public interface ContactDAO {

    public int save(Contact contact);

    public int update(Contact contact);

    public Contact get(Integer id);

    public int delete(Integer id);

    public List<Contact> list();


}
