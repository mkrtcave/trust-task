package trask.junior.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trask.junior.task.model.Technology;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    boolean existsById(Long id);

    boolean existsByName(String name);

    void deleteById(Long id);
}
