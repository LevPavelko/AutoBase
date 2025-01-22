package AutoBase.dto;

import lombok.Data;

@Data
public class DriverDTO {
    private Long id;
    private UserDTO userDTO;
    private int experienceInYears;
    private boolean isBusy;

}
