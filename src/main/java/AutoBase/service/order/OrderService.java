package AutoBase.service.order;

import AutoBase.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
//    void save(OrderDTO orderDTO);
//    void update(OrderDTO orderDTO);
//    void delete(OrderDTO orderDTO);
    List<OrderDTO> findAll();
    //Optional<OrderDTO> findById(int id);
}
