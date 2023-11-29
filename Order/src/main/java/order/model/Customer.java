package order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

    private Long id;

    private String companyName;
    private String address;
    private String country;

    private Contact contact;

    public Customer() {}

    public Customer(Long id, String companyName, String address, String country) {
        this.id = id;
        this.companyName = companyName;
        this.address = address;
        this.country = country;
    }

    public Customer(Long id, String companyName, String address, String country, Contact contact) {
        this.id = id;
        this.companyName = companyName;
        this.address = address;
        this.country = country;
        this.contact = contact;
    }


    public void setCustomer (Customer customer) {
        if (customer.address != null) {
            setAddress(customer.address);
        }
        if (customer.country != null) {
            setCountry(customer.country);
        }
        if (customer.companyName != null) {
            setCompanyName(customer.companyName);
        }
        if (customer.contact != null) {
            contact.setContact(customer.contact);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
