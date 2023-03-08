package trask.junior.task.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import trask.junior.task.dto.CandidateDTO;
import trask.junior.task.dto.TechnologyDTO;
import trask.junior.task.model.Candidate;
import trask.junior.task.model.Technology;
import trask.junior.task.repository.TechnologyRepository;
import trask.junior.task.service.TechnologyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyRepository technologyRep;

    private final ModelMapper modelMapper;

    public TechnologyDTO create(TechnologyDTO dto){
        checkIfExistByName(dto.getName());
        Technology technology = modelMapper.map(dto, Technology.class);
        return modelMapper.map(technologyRep.save(technology), TechnologyDTO.class);
    }

    public List<TechnologyDTO> getAll(){
        return technologyRep.findAll()
                .stream()
                .map(technology -> modelMapper.map(technology, TechnologyDTO.class))
                .collect(Collectors.toList());
    }

    public TechnologyDTO update(Long id, TechnologyDTO dto){
        checkIfExistById(id);
        checkIfExistByName(dto.getName());
        Technology technology = modelMapper.map(dto, Technology.class);
        technology.setId(id);
        return modelMapper.map(technologyRep.save(technology), TechnologyDTO.class);
    }

    public List<CandidateDTO> getAllCandidates(Long technologyId){
        checkIfExistById(technologyId);
        Technology technology = technologyRep.getReferenceById(technologyId);
        return technology.getCandidates()
                .stream()
                .map(candidateTechnology -> modelMapper.map(candidateTechnology.getCandidate(), CandidateDTO.class))
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        checkIfExistById(id);
        technologyRep.deleteById(id);
    }

    public void checkIfExistById(Long id){
        boolean exists = technologyRep.existsById(id);
        if(!exists){
            throw new IllegalStateException("Technology with id " + id + " does not exist");
        }
    }

    public void checkIfExistByName(String name){
        boolean exists = technologyRep.existsByName(name);
        if(exists){
            throw new IllegalStateException("Technology with name " + name + " already exists");
        }
    }
}
