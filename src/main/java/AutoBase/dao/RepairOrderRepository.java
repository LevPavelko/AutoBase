package AutoBase.dao;

import AutoBase.model.Order;
import AutoBase.model.RepairOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {
}
