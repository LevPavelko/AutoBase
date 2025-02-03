package AutoBase.controller;

import AutoBase.dto.*;
import AutoBase.model.CarType;
import AutoBase.model.CargoType;
import AutoBase.service.car.CarServiceImpl;
import AutoBase.service.driver.DriverServiceImpl;
import AutoBase.service.order.OrderServiceImpl;
import AutoBase.service.repair_order_service.RepairOrderServiceImpl;
import AutoBase.service.trip.TripServiceImpl;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
@AutoConfigureMockMvc
public class RepairOrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private DriverServiceImpl driverService;

    @MockBean
    private CarServiceImpl carService;

    @MockBean
    private OrderServiceImpl orderService;


    @MockBean
    private TripServiceImpl tripService;

    @MockBean
    private RepairOrderServiceImpl repairOrderService;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testGetRepairOrders_ShouldReturnAllOrders_WhenRequestSendFromDispatcher() throws Exception {
        List<RepairOrderDTO> repairOrderDTOS = new ArrayList<>();
        repairOrderDTOS.add(createRepairOrder());
        List<RepairOrderDTO> repairedOrders = repairOrderDTOS.stream()
                .filter(RepairOrderDTO -> !RepairOrderDTO.isRepaired())
                .collect(Collectors.toList());

        when(repairOrderService.findAll()).thenReturn(repairedOrders);
        mockMvc.perform(get("/repairOrders").with(user("popov.alex@gmail.com").password("123").roles("DISPATCHER")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("repairOrders", repairOrderService.findAll()))
                .andExpect(view().name("repairOrders"));

    }

    @Test
    public void testRepairOrderForm_ShouldSaveRepairOrderAndRedirect_WhenDataIsValid() throws Exception {
        Long tripId = 1L;
        Long carId = 2L;
        Long driverId = 3L;

        TripDTO tripDTO = createTrip();
        tripDTO.setId(tripId);

        CarDTO carDTO = createCar();
        carDTO.setId(carId);
        carDTO.setBroken(false);

        DriverDTO driverDTO = createDriver();
        driverDTO.setId(driverId);

        RepairOrderDTO repairOrderDTO = createRepairOrder();

        when(tripService.findById(tripId)).thenReturn(Optional.of(tripDTO));
        when(carService.findById(carId)).thenReturn(Optional.of(carDTO));
        when(driverService.findById(driverId)).thenReturn(Optional.of(driverDTO));


        mockMvc.perform(post("/RepairOrderForm")
                        .with(user("dispatcher@gmail.com").password("123").roles("DISPATCHER"))
                        .param("tripId", tripId.toString())
                        .param("carId", carId.toString())
                        .param("driverId", driverId.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("/home"));



        verify(carService, times(1)).update(any(CarDTO.class));
        verify(repairOrderService, times(1)).save(any(RepairOrderDTO.class));
    }

    public TripDTO createTrip() {
        TripDTO tripDTO = new TripDTO();

        tripDTO.setDriver(createDriver());
        tripDTO.setCar(createCar());
        tripDTO.setStart_date(LocalDate.now());
        tripDTO.setEnd_date(null);
        tripDTO.setPrice(1500);

        tripDTO.setOrder(createOrder(createDispatcher()));

        return tripDTO;


    }

    public CarDTO createCar(){
        CarDTO carDTO = new CarDTO();
        carDTO.setCarType(CarType.REFRIGERATED_VAN);
        carDTO.setFree(true);
        carDTO.setCapacity(6);
        carDTO.setBroken(false);
        return carDTO;
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

    public DriverDTO createDriver(){
        DriverDTO driverDTO = new DriverDTO();
        UserDTO userDTO = new UserDTO();

        UserRoleDTO roleDTO = new UserRoleDTO();

        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        roleDTO.setRole("ROLE_DRIVER");
        userDTO.setEmail("john@doe.com");
        userDTO.setPassword("password");
        userDTO.setAge(32);
        userDTO.setRoleDTO(roleDTO);
        driverDTO.setUserDTO(userDTO);
        return driverDTO;
    }

    public RepairOrderDTO createRepairOrder(){
        RepairOrderDTO repairOrderDTO = new RepairOrderDTO();
        repairOrderDTO.setDriver(createDriver());
        repairOrderDTO.setCar(createCar());
        repairOrderDTO.setDescription("lalala");
        repairOrderDTO.setTrip(createTrip());
        repairOrderDTO.setRequestDate(LocalDate.now());
        repairOrderDTO.setRepaired(false);
        return repairOrderDTO;
    }



}
