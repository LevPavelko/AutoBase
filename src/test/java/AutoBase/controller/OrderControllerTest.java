package AutoBase.controller;


import AutoBase.dto.*;
import AutoBase.model.CargoType;
import AutoBase.service.car.CarServiceImpl;
import AutoBase.service.driver.DriverServiceImpl;
import AutoBase.service.order.OrderServiceImpl;
import AutoBase.service.role.RoleServiceImpl;
import AutoBase.service.trip.TripServiceImpl;
import AutoBase.service.user_service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testGetOrders_ShouldReturnAllOrders_WhenRequestSendFromDispatcher() throws Exception {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orderDTOS.add(createOrder(createDispatcher()));
        List<OrderDTO> sortedByDateDesc = orderDTOS.stream()
                .filter(order -> !order.isHasTrip())
                .sorted(Comparator.comparing(OrderDTO::getRequestData).reversed())
                .collect(Collectors.toList());



        when(orderService.findAll()).thenReturn(sortedByDateDesc);
        mockMvc.perform(get("/orders").with(user("popov.alex@gmail.com").password("123").roles("DISPATCHER")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("orders", orderService.findAll()))
                .andExpect(view().name("orders"));

    }

    public DispatcherDTO createDispatcher(){
        DispatcherDTO dispatcherDTO = new DispatcherDTO();
        UserDTO user2DTO = new UserDTO();
        UserRoleDTO role2DTO = new UserRoleDTO();

        user2DTO.setFirstName("Michael");
        user2DTO.setLastName("Doe");
        role2DTO.setRole("ROLE_DISPATCHER");
        user2DTO.setEmail("Michael@doe.com");
        user2DTO.setPassword("password");
        user2DTO.setAge(32);
        user2DTO.setRoleDTO(role2DTO);
        dispatcherDTO.setUserDTO(user2DTO);
        return dispatcherDTO;
    }

    public OrderDTO createOrder(DispatcherDTO dispatcherDTO){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCargoType(CargoType.BULK);
        orderDTO.setCargoWeight(2);
        orderDTO.setRequestData(LocalDate.now());
        orderDTO.setDestination("Colorado");
        orderDTO.setHasTrip(true);
        orderDTO.setDispatcher(dispatcherDTO);
        return orderDTO;
    }
}

