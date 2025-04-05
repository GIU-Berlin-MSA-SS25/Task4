package scalable.tasks.Task_4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scalable.tasks.Task_4.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
