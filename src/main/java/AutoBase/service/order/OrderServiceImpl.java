package AutoBase.service.order;

import AutoBase.dao.OrderRepository;
import AutoBase.dto.OrderDTO;
import AutoBase.convert.*;
import AutoBase.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
   @Autowired
    private OrderRepository orderRepository;

   @Autowired
   private Converter converter;

   @Override
    public List<OrderDTO> findAll() {
       List<Order> orders = orderRepository.findAll();
       return orders.stream()
               .map(converter::convertOrderToDTO)
               .collect(Collectors.toList());

   }

   @Override
   public void delete (OrderDTO orderDTO) {
       Order order = converter.convertToEntity(orderDTO);
       orderRepository.delete(order);
   }

    @Override
    public Optional<OrderDTO> findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(converter::convertOrderToDTO);
    }

    @Override
    public void update(OrderDTO orderDTO) {
       Order order = converter.convertToEntity(orderDTO);
       orderRepository.save(order);
    }

}
