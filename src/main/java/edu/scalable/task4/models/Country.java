package edu.scalable.task4.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    List<Employee> employees;

    @JsonIgnore
    @ManyToMany(mappedBy = "countries")
    List<Company> companies;

    public Country() {
        this.employees = new ArrayList<Employee>();
        this.companies = new ArrayList<Company>();
    }

    public Country(String name) {
        this.name = name;
        this.employees = new ArrayList<Employee>();
        this.companies = new ArrayList<Company>();
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

    public List<Employee> getEmployees() { return employees;}

    public void setEmployees(List<Employee> employees) { this.employees = employees;}

    public List<Company> getCompanies() { return companies;}

    public void setCompanies(List<Company> companies) { this.companies = companies;}
}
