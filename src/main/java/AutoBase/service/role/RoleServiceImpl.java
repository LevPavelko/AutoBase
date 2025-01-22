package AutoBase.service.role;

import AutoBase.convert.Converter;
import AutoBase.dao.UserRoleRepository;
import AutoBase.dto.UserRoleDTO;
import AutoBase.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private Converter converter;

    @Override
    public Optional<UserRoleDTO> findById(Long id) {
        Optional<UserRole> userRole = userRoleRepository.findById(id);
        return userRole.map(converter::convertUserRoleToDTO);
    }
}
