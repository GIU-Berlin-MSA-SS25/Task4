package edu.scalable.task4.models;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    Company company;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    Country country;

    public Employee() {}

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Employee(String name, String email, Company company) {
        this.name = name;
        this.email = email;
        this.company = company;
    }

    public Employee(String name, String email, Country country) {
        this.name = name;
        this.email = email;
        this.country = country;
    }

    public Employee(String name, String email, Company company, Country country) {
        this.name = name;
        this.email = email;
        this.company = company;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Company getCompany() { return company; }

    public void setCompany(Company company) { this.company = company; }

    public Country getCountry() { return country; }

    public void setCountry(Country country) { this.country = country; }
}
