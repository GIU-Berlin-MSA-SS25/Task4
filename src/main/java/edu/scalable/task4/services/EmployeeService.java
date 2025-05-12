package edu.scalable.task4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.scalable.task4.models.Employee;
import edu.scalable.task4.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        else {
            throw new RuntimeException("Employee not found");
        }
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setCompany(updatedEmployee.getCompany());
            existingEmployee.setCountry(updatedEmployee.getCountry());
            return employeeRepository.save(existingEmployee);
        } else {
            throw new RuntimeException("Employee with id " + id + " not found");
        }
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
}
