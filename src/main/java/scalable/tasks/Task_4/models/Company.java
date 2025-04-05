package scalable.tasks.Task_4.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    List<Employee> employees;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "company_country",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private List<Country> countries;

    public Company() {
        this.employees = new ArrayList<>();
        this.countries = new ArrayList<>();
    }

    public Company(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
        this.countries = new ArrayList<>();
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

    public List<Employee> getEmployees() { return employees; }

    public void setEmployees(List<Employee> employees) { this.employees = employees; }

    public List<Country> getCountries() { return countries; }

    public void setCountries(List<Country> countries) { this.countries = countries; }
}