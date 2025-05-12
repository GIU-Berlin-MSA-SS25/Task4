package edu.scalable.task4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import edu.scalable.task4.models.Company;
import edu.scalable.task4.services.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCourses() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable int id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public Company createCompany(@RequestBody Company company) {
        return companyService.saveCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable int id, @RequestBody Company newCompany) {
        return companyService.updateCompany(id, newCompany);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
    }
}
