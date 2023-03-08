package trask.junior.task.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trask.junior.task.dto.CandidateDTO;
import trask.junior.task.dto.TechnologyDTO;
import trask.junior.task.service.impl.TechnologyServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/technologies")
public class TechnologyController {

    private final TechnologyServiceImpl technologyServiceImpl;

    @PostMapping
    public ResponseEntity<TechnologyDTO> create(@RequestBody TechnologyDTO dto){
        return new ResponseEntity<>(technologyServiceImpl.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TechnologyDTO>> getAll(){
        return new ResponseEntity<>(technologyServiceImpl.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}/candidates")
    public ResponseEntity<List<CandidateDTO>> getAllCandidates(@PathVariable Long id){
        return new ResponseEntity<>(technologyServiceImpl.getAllCandidates(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechnologyDTO> update(@PathVariable Long id, @RequestBody TechnologyDTO technology){
        return new ResponseEntity<>(technologyServiceImpl.update(id, technology), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        technologyServiceImpl.delete(id);
        return HttpStatus.OK;
    }


}
