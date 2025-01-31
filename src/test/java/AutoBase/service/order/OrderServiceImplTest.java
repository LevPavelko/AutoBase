package AutoBase.service.order;

import AutoBase.AutoBaseApplication;
import AutoBase.convert.Converter;
import AutoBase.dao.*;
import AutoBase.model.CargoType;
import AutoBase.model.Dispatcher;
import AutoBase.model.Order;
import AutoBase.model.Trip;
import AutoBase.utils.TestConteinersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(scripts = {"classpath:schema.sql"})
@ContextConfiguration(classes = AutoBaseApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
public class OrderServiceImplTest {
    @Autowired
    private Converter converter;

    @Autowired
    private TripRepository tripRepository;



    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestConteinersConfiguration testConteinersConfiguration;
    @Autowired
    private DispatcherRepository dispatcherRepository;

    @Test
    @Transactional
    @Sql("classpath:addOrders.sql")
    public void save_ShouldReturnNewRowInDB_WhenCalled() {
        Optional<Order> actualOrder = orderRepository.findById(1L);
        Order expectedOrder = this.createOrder();
        assertEquals(expectedOrder,
                actualOrder.get(),
                "Execute method save from orders");

    }

    @Test
    @Transactional
    @Sql("classpath:addOrders.sql")
    public void findAll_ShouldReturnListOfOrders_WhenCalled() {
        List<Order> actualOrders = orderRepository.findAll();
        List<Order> expectedOrders = createOrders();
        assertEquals(expectedOrders,
                actualOrders,
                "Execute method find all from orders");
    }

    @Test
    @Transactional
    @Sql("classpath:addOrders.sql")
    public void findById_ShouldReturnOrderObject_WhenCalled() {
        Optional<Order> actualResult = orderRepository.findById(1L);
        Order expectedOrder= this.createOrder();
        assertEquals(expectedOrder, actualResult.get(), "Execute method findById from orders");
    }

    public Order createOrder(){
        Order order = new Order();
        Optional<Dispatcher> dispatcher = dispatcherRepository.findById(1L);
        order.setId(1L);
        order.setCargoType(CargoType.DUSTED);
        order.setCargoWeight(6);
        order.setRequestData(LocalDate.now());
        order.setDispatcher(dispatcher.get());
        order.setDestination("Colorado");
        order.setHasTrip(false);
        return order;
    }

    public List<Order> createOrders(){
        List<Order> orders = new ArrayList<>();

        Order order1 = new Order();
        Optional<Dispatcher> dispatcher = dispatcherRepository.findById(1L);
        order1.setId(1L);
        order1.setCargoType(CargoType.DUSTED);
        order1.setCargoWeight(6);
        order1.setRequestData(LocalDate.now());
        order1.setDispatcher(dispatcher.get());
        order1.setDestination("Colorado");
        order1.setHasTrip(false);
        orders.add(order1);

        Order order2 = new Order();
        order2.setId(2L);
        order2.setCargoType(CargoType.DUSTED);
        order2.setCargoWeight(6);
        order2.setRequestData(LocalDate.now());
        order2.setDispatcher(dispatcher.get());
        order2.setDestination("Colorado");
        order2.setHasTrip(false);
        orders.add(order2);


        return orders;
    }

}
