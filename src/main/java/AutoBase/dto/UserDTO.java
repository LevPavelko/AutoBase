package AutoBase.dto;

import AutoBase.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private  Long id;

    private  String firstName;

    private  String lastName;

    private int age;

    private String email;

    private String password;

    private UserRoleDTO roleDTO;
}
