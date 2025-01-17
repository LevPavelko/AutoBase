package AutoBase.service.user_service;

import AutoBase.convert.Converter;
import AutoBase.dao.UserRepository;
import AutoBase.dao.UserRoleRepository;
import AutoBase.dto.UserDTO;
import AutoBase.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private Converter converter;

    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String roleName = userRoleRepository.getRoleNameByUserId(user.getId());
        if (roleName == null) {
            throw new UsernameNotFoundException("User has no role assigned");
        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        grantList.add(new SimpleGrantedAuthority(roleName));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantList);

    }

    @Override
    public Long save(UserDTO userDTO)
    {
        User user = converter.convertToEntity(userDTO);
        userRepository.save(user);
        return user.getId();

    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(converter::convertUserToDTO);
    }
}
