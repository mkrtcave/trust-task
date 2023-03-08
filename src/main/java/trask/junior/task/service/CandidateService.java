package trask.junior.task.service;

import trask.junior.task.dto.CandidateDTO;
import trask.junior.task.dto.CandidateTechnologyDTO;
import java.util.List;

/**
 * Candidate service implementation class can be used to save new candidate.
 * Also provides methods for fetching existing entities from database.
 */
public interface CandidateService {

    /**
     * Save new candidate to DB.
     * Throws exception when candidate with selected email already exists.
     *
     *  @param dto - dto object contains data about candidate, should be transformed to entity class
     *  @return dto object with added id after saving toDB.
     */
    public CandidateDTO create(CandidateDTO dto);

    /**
     * Return all candidates drom DB.
     *
     *  @return List of candidate dto objects with.
     */
    public List<CandidateDTO> getAll();

    /**
     *  Update candidate data to database.
     *  Throws exception when candidate with selected id does not exist
     *  Throws exception when new email already exist in DB.
     *
     *  @param id of candidate to be updated
     *  @param dto object with new data of candidate
     *  @return dto object with new data of candidate.
     */
    public CandidateDTO update(Long id, CandidateDTO dto);

    /**
     *  Add technology to candidate technology list
     *  Throws exception when candidate with selected id does not exist
     *  Throws exception when selected technology does not exist
     *
     *  @param id of candidate to be added new technology
     *  @param dto object with technology and other data ( level, note )
     */
    public void addTechnology(Long id, CandidateTechnologyDTO dto);

    /**
     *  Return all technologies of candidate
     *  Throws exception when candidate with selected id does not exist
     *
     *  @param id of candidate to be returned new technology
     *  @return List of dto objects with technologies and other data ( level, note )
     */
    public List<CandidateTechnologyDTO> getTechnologies(Long id);

    /**
     *  Delete candidate form DB
     *  Throws exception when candidate with selected id does not exist
     *
     *  @param id of candidate to be deleted
     */
    public void delete(Long id);
}
