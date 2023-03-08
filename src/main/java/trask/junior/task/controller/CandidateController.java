package trask.junior.task.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trask.junior.task.dto.CandidateDTO;
import trask.junior.task.dto.CandidateTechnologyDTO;
import trask.junior.task.service.impl.CandidateServiceImpl;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateServiceImpl candidateServiceImpl;


    @PostMapping
    public ResponseEntity<CandidateDTO> create(@RequestBody CandidateDTO dto){
        return new ResponseEntity<>(candidateServiceImpl.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getAll(){
        return new ResponseEntity<>(candidateServiceImpl.getAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateDTO> update(@PathVariable Long id, @RequestBody CandidateDTO dto){
        return new ResponseEntity<>(candidateServiceImpl.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        candidateServiceImpl.delete(id);
        return HttpStatus.OK;
    }

    @PostMapping("/{id}/technologies")
    public void addTechnologiesToCandidate(@PathVariable Long id, @RequestBody CandidateTechnologyDTO dto){
        candidateServiceImpl.addTechnology(id, dto);
    }

    @GetMapping("/{id}/technologies")
    public ResponseEntity<List<CandidateTechnologyDTO>> getTechnologiesOfCandidate(@PathVariable Long id){
        return new ResponseEntity<>(candidateServiceImpl.getTechnologies(id), HttpStatus.OK);
    }

}
