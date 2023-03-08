package trask.junior.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trask.junior.task.model.CandidateTechnology;

import java.util.List;

public interface CandidateTechnologyRepository extends JpaRepository<CandidateTechnology, Long> {
    List<CandidateTechnology> findByCandidateIdAndTechnologyId(Long candidateId, Long technologyId);

    void deleteById(Long id);
}
