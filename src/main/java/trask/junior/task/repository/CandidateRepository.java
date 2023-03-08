package trask.junior.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trask.junior.task.model.Candidate;
import trask.junior.task.model.CandidateTechnology;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    boolean existsById(Long id);

    void deleteById(Long id);

    boolean existsByEmail(String email);
}
