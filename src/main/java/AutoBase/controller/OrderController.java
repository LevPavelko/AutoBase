package AutoBase.controller;

import AutoBase.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/orders")
    public String getOrders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }
}
