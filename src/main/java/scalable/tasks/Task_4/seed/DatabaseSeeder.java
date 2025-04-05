package scalable.tasks.Task_4.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import scalable.tasks.Task_4.models.Company;
import scalable.tasks.Task_4.models.Country;
import scalable.tasks.Task_4.models.Employee;
import scalable.tasks.Task_4.repositories.CompanyRepository;
import scalable.tasks.Task_4.repositories.CountryRepository;
import scalable.tasks.Task_4.repositories.EmployeeRepository;

import java.util.Arrays;

@Component
public class DatabaseSeeder {

    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public DatabaseSeeder(CompanyRepository companyRepository,
                          CountryRepository countryRepository,
                          EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void seedDatabase() {
        // Clean existing data first
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
        countryRepository.deleteAll();

        // 1. Create Countries
        Country usa = new Country("USA");
        Country canada = new Country("Canada");
        Country uk = new Country("UK");

        countryRepository.saveAll(Arrays.asList(usa, canada, uk));

        // 2. Create Companies
        Company companyA = new Company("Company A");
        Company companyB = new Company("Company B");
        Company companyC = new Company("Company C");

        // Establish many-to-many relationship: assign countries to companies
        companyA.getCountries().add(usa);
        companyA.getCountries().add(canada);

        companyB.getCountries().add(usa);
        companyB.getCountries().add(uk);

        companyC.getCountries().add(canada);
        companyC.getCountries().add(uk);

        companyRepository.saveAll(Arrays.asList(companyA, companyB, companyC));

        // 3. Create Employees and set associations
        Employee emp1 = new Employee("Alice", "alice@example.com", companyA, usa);
        Employee emp2 = new Employee("Bob", "bob@example.com", companyA, canada);
        Employee emp3 = new Employee("Charlie", "charlie@example.com", companyB, usa);
        Employee emp4 = new Employee("Diana", "diana@example.com", companyB, uk);
        Employee emp5 = new Employee("Eve", "eve@example.com", companyC, canada);
        Employee emp6 = new Employee("Frank", "frank@example.com", companyC, uk);

        employeeRepository.saveAll(Arrays.asList(emp1, emp2, emp3, emp4, emp5, emp6));

        // 4. Update associations for Company and Country (bidirectional mapping)

        // For Companies: add employees to company lists
        companyA.getEmployees().add(emp1);
        companyA.getEmployees().add(emp2);
        companyB.getEmployees().add(emp3);
        companyB.getEmployees().add(emp4);
        companyC.getEmployees().add(emp5);
        companyC.getEmployees().add(emp6);

        companyRepository.saveAll(Arrays.asList(companyA, companyB, companyC));

        // For Countries: add employees to country lists
        usa.getEmployees().add(emp1);
        usa.getEmployees().add(emp3);
        canada.getEmployees().add(emp2);
        canada.getEmployees().add(emp5);
        uk.getEmployees().add(emp4);
        uk.getEmployees().add(emp6);

        countryRepository.saveAll(Arrays.asList(usa, canada, uk));
    }
}
