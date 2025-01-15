package AutoBase.controller;

import AutoBase.dao.OrderRepository;
import AutoBase.dto.OrderDTO;
import AutoBase.model.Order;
import AutoBase.service.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Controller
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping(value = "/orders")
    public String getOrders(Model model) {
        List<OrderDTO> orders = orderService.findAll();
        List<OrderDTO> sortedByDateDesc = orders.stream()
                .filter(order -> !order.isHasTrip())
                .sorted(Comparator.comparing(OrderDTO::getRequestData).reversed())
                .collect(Collectors.toList());
//        List<OrderDTO> availableOrders;
//        availableOrders = orders.stream()
//                .filter(order -> !order.isHasTrip())
//                .collect(Collectors.toList());

        model.addAttribute("orders", sortedByDateDesc);
        return "orders";
    }

}
