package AutoBase.service.user_service;

import AutoBase.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    Long save(UserDTO userDTO);
    Optional<UserDTO> findById(Long id);
}
