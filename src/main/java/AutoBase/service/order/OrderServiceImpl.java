package AutoBase.service.order;

import AutoBase.dao.OrderRepository;
import AutoBase.dto.OrderDTO;
import AutoBase.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
   @Autowired
    private OrderRepository orderRepository;

   @Override
    public List<OrderDTO> findAll() {
       List<Order> orders = orderRepository.findAll();
       return orders.stream()
               .map(order -> new OrderDTO(
                       order.getId(),
                       order.getCargoType(),
                       order.getCargoWeight(),
                       order.getRequestData(),
                       order.getDestination(),
                       order.getDispatcher() != null ? order.getDispatcher().getId() : null))
               .collect(Collectors.toList());

   }
}
