package trask.junior.task.dto;

import lombok.Data;

@Data
public class CandidateDTO {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String phoneNumber;
}
