package edu.scalable.task4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.scalable.task4.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
