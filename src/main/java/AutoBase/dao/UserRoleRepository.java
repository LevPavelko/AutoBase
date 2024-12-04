package AutoBase.dao;

import AutoBase.model.User;
import AutoBase.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
//    @Query("SELECT ur.role FROM UserRole ur WHERE ur.id = ?1")
//    String getRoleNameByUserId(Long userId);
@Query("SELECT ur.role FROM User u JOIN u.role ur WHERE u.id = ?1")
String getRoleNameByUserId(Long userId);

}
