package th.grzegorzNauka.contact.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import th.grzegorzNauka.contact.model.Contact;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ContactDAOImpl implements ContactDAO{

    private JdbcTemplate jdbcTemplate;

    public ContactDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }

    @Override
    public int save(Contact contact) {
        String sql = "INSERT INTO contact (name, email, adress, phone) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, contact.getName(),contact.getEmail(), contact.getAdress(), contact.getPhone());

       // return 0;
    }

    @Override
    public int update(Contact contact) {
        String sql ="UPDATE contact SET name=?, email=?, adress=?, phone=? WHERE contact_id=?";
        return jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getAdress(),
                contact.getPhone(), contact.getId());

    }

    @Override
    public Contact get(Integer id) {
        String sql = "SELECT * FROM CONTACT WHERE contact_id="+ id;

        ResultSetExtractor<Contact> extractor = new ResultSetExtractor<Contact>() {
            @Override
            public Contact extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String adress = resultSet.getString("adress");
                    String phone = resultSet.getString("phone");

                    return new Contact(id, name, email, adress, phone);
                }
                return null;
            }
        };


        return jdbcTemplate.query(sql, extractor);
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM contact WHERE contact_id=" + id;
        return jdbcTemplate.update(sql);
    }

    @Override
    public List<Contact> list() {
        String sql = "SELECT * FROM contact";

        RowMapper<Contact> rowMapper = new RowMapper<Contact>() {
            @Override
            public Contact mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
                Integer id = resultSet.getInt("contact_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String adress = resultSet.getString("adress");
                String phone = resultSet.getString("phone");


                return new Contact(id,name,email,adress,phone);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }
}
