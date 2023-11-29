package order.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact {

    private Long id;

    private String name;
    private String phone;
    private String email;
    private String position;

    private Customer customer;

    public Contact () {}

    public Contact(Long id, String name, String phone, String email, String position) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return " ID =' " + id  +
                " Name =' " + name +
                ", Phone =' " + phone +
                ", Email =' " + email +
                ", Position ='" + position;
    }

    public void setContact(Contact contact) {
        if (contact.phone != null) {
            this.phone = contact.phone;
        }
        if (contact.email != null) {
            this.email = contact.email;
        }
        if (contact.name != null) {
            this.name = contact.name;
        }
        if (contact.position != null) {
            this.position = contact.position;
        }
    }

}
