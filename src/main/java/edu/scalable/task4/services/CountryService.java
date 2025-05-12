package edu.scalable.task4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.scalable.task4.models.Country;
import edu.scalable.task4.repositories.CountryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryById(Integer id) {
        Optional<Country> country = countryRepository.findById(id);
        if (country.isPresent()) {
            return country.get();
        }
        else {
            throw new RuntimeException("Country not found");
        }
    }

    public Country updateCountry(Integer id, Country updatedCountry) {
        Optional<Country> optionalCountry = countryRepository.findById(id);

        if (optionalCountry.isPresent()) {
            Country existingCountry = optionalCountry.get();
            existingCountry.setName(updatedCountry.getName());
            existingCountry.setEmployees(updatedCountry.getEmployees());
            existingCountry.setCompanies(updatedCountry.getCompanies());
            return countryRepository.save(existingCountry);
        } else {
            throw new RuntimeException("Country with id " + id + " not found");
        }
    }

    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    public void deleteCountry(Integer id) {
        countryRepository.deleteById(id);
    }
}
