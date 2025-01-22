package AutoBase.service.role;

import AutoBase.dto.UserRoleDTO;

import javax.management.relation.Role;
import java.util.Optional;

public interface RoleService {
    Optional<UserRoleDTO> findById(Long id);
}
