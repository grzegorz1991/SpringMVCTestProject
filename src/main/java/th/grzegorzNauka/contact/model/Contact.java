package th.grzegorzNauka.contact.model;

public class Contact {
    private Integer id;
    private String name;
    private String email;
    private String adress;
    private String phone;
    public Contact(Integer id, String name, String email, String adress, String phone) {

        this(name,email,adress,phone);
        this.id = id;
    }
    public Contact(String name, String email, String adress, String phone) {

        this.name = name;
        this.email = email;
        this.adress = adress;
        this.phone = phone;
    }

    public Contact() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
