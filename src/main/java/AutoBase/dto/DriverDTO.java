package AutoBase.dto;

import lombok.Data;

@Data
public class DriverDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private int experienceInYears;
    private boolean isBusy;

}
