package scalable.tasks.Task_4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scalable.tasks.Task_4.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
