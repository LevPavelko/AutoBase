package AutoBase.dao;


import AutoBase.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {
}
