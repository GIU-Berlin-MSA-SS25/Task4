package edu.scalable.task4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import edu.scalable.task4.models.Company;
import edu.scalable.task4.repositories.CompanyRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            return company.get();
        } else {
            throw new RuntimeException("Company not found");
        }
    }

    public Company updateCompany(Integer id, Company updatedCompany) {
        Optional<Company> optionalCompany = companyRepository.findById(id);

        if (optionalCompany.isPresent()) {
            Company existingCompany = optionalCompany.get();
            existingCompany.setName(updatedCompany.getName());
            existingCompany.setEmployees(updatedCompany.getEmployees());
            existingCompany.setCountries(updatedCompany.getCountries());
            return companyRepository.save(existingCompany);
        } else {
            throw new RuntimeException("Company with id " + id + " not found");
        }
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }

}
