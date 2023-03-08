package trask.junior.task.service;

import trask.junior.task.dto.CandidateDTO;
import trask.junior.task.dto.TechnologyDTO;

import java.util.List;

/**
 * Technology service implementation class can be used to save new technology.
 * Also provides methods for fetching existing entities from database.
 */
public interface TechnologyService {

    /**
     * Save new technology to database.
     *  Throws exception when technology with name already exists.
     *
     *  @param dto - dto object contains information about technology, should be transformed to entity class
     *  @return dto object with added id after saving DB.
     */
    TechnologyDTO create(TechnologyDTO dto);

    /**
     *  Return all technologies from database.
     *
     *  @return List of dto objects from DB.
     */
    List<TechnologyDTO> getAll();

    /**
     *  Update data about technology to database.
     *  Throws exception when technology with name already exists.
     *  Throws exception when technology with selected id does not exist.
     *
     *  @param dto - dto object with new data about technology
     *  @return dto object with new data.
     */
    TechnologyDTO update(Long id, TechnologyDTO dto);


    /**
     *  Return all candidates who have this technology.
     *  Throws exception when technology with selected id does not exist.
     *
     *  @param technologyId - id technology to which the candidates will be returned
     *  @return List of candidate dto objects
     */
    List<CandidateDTO> getAllCandidates(Long technologyId);


    /**
     *  Delete technology from DB.
     *  Throws exception when technology with selected id does not exist.
     *
     *  @param id - id technology to be wanted to delete
     *  @return List of candidate dto objects
     */
    void delete(Long id);
}
