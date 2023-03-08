package trask.junior.task.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import trask.junior.task.dto.CandidateDTO;
import trask.junior.task.dto.CandidateTechnologyDTO;
import trask.junior.task.model.Candidate;
import trask.junior.task.model.CandidateTechnology;
import trask.junior.task.model.Level;
import trask.junior.task.model.Technology;
import trask.junior.task.repository.CandidateRepository;
import trask.junior.task.repository.CandidateTechnologyRepository;
import trask.junior.task.repository.TechnologyRepository;
import trask.junior.task.service.CandidateService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRep;

    private final TechnologyRepository technologyRep;

    private final CandidateTechnologyRepository candidateTechnologyRep;

    private final ModelMapper modelMapper;

    public CandidateDTO create(CandidateDTO dto){
        checkIfExistByEmail(dto.getEmail());
        Candidate candidate = modelMapper.map(dto, Candidate.class);
        return modelMapper.map(candidateRep.save(candidate), CandidateDTO.class);
    }

    public List<CandidateDTO> getAll(){
        return candidateRep.findAll()
                .stream()
                .map(candidate -> modelMapper.map(candidate, CandidateDTO.class))
                .collect(Collectors.toList());
    }

    public CandidateDTO update(Long id, CandidateDTO dto){
        checkIfExistById(id);
        checkIfExistByEmail(dto.getEmail());
        Candidate candidate = modelMapper.map(dto,Candidate.class);
        candidate.setId(id);
        CandidateDTO responseDTO = modelMapper.map(candidateRep.save(candidate), CandidateDTO.class);
        return responseDTO;
    }

    public void addTechnology(Long id, CandidateTechnologyDTO dto){
        checkIfExistById(id);
        checkIfExistById(dto.getTechnologyId());
        List<CandidateTechnology> canTech = candidateTechnologyRep.findByCandidateIdAndTechnologyId(id, dto.getTechnologyId());
        if(!canTech.isEmpty()){
            throw new IllegalStateException("Candidate already has this technology");
        }
        Candidate candidate = candidateRep.getReferenceById(id);
        Technology technology = technologyRep.getReferenceById(dto.getTechnologyId());
        CandidateTechnology ct = CandidateTechnology.builder()
                .technology(technology)
                .candidate(candidate)
                .level(Level.getLevel(dto.getLevelOfTechnology()))
                .note(dto.getNote())
                .build();
        candidateTechnologyRep.save(ct);
        candidate.getTechnologies().add(ct);
        technology.getCandidates().add(ct);
        candidateRep.save(candidate);
        technologyRep.save(technology);
    }

    public List<CandidateTechnologyDTO> getTechnologies(Long id){
        checkIfExistById(id);
        Candidate candidate = candidateRep.getReferenceById(id);
        List<CandidateTechnologyDTO> responseDto = new ArrayList<CandidateTechnologyDTO>();
        for(CandidateTechnology ct : candidate.getTechnologies()){
            CandidateTechnologyDTO ctd = modelMapper.map(ct, CandidateTechnologyDTO.class);
            ctd.setNameOfTechnology(ct.getTechnology().getName());
            ctd.setLevelOfTechnology(ct.getLevel().getNum());
            responseDto.add(ctd);
        }
        return responseDto;
    }

    public void delete(Long id){
        checkIfExistById(id);
        candidateRep.deleteById(id);
    }

    public void checkIfExistById(Long id){
        boolean exists = candidateRep.existsById(id);
        if(!exists){
            throw new IllegalStateException("Candidate with id " + id + " does not exist");
        }
    }

    public void checkIfExistByEmail(String email){
        boolean exists = candidateRep.existsByEmail(email);
        if(exists){
            throw new IllegalStateException("Candidate with email " + email + " already exists");
        }
    }

}
