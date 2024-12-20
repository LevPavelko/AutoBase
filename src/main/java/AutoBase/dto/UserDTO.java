package AutoBase.dto;

import AutoBase.model.UserRole;
import lombok.Data;


@Data
public class UserDTO {
    private  Long id;

    private  String firstName;

    private  String lastName;

    private int age;

    private String email;

    private String password;

    private UserRoleDTO roleDTO;
}
