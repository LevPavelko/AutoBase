package AutoBase.service.repair_order_service;

import AutoBase.AutoBaseApplication;
import AutoBase.dao.CarRepository;
import AutoBase.dao.DriverRepository;
import AutoBase.dao.RepairOrderRepository;
import AutoBase.dao.TripRepository;
import AutoBase.model.Car;
import AutoBase.model.Driver;
import AutoBase.model.RepairOrder;
import AutoBase.model.Trip;
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
public class RepairOrderServiceImplTest {

    @Autowired
    private RepairOrderRepository repairOrderRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TripRepository tripRepository;

    @Test
    @Transactional
    @Sql("classpath:addRepairOrders.sql")
    public void save_ShouldAddRepairOrder_WhenCalled() {
        Optional<RepairOrder> actualResult = repairOrderRepository.findById(1L);
        RepairOrder expectedResult = createRepairOrder(1L);
        assertEquals(expectedResult,
                actualResult.get(),
                "Execute method save from repair orders");
    }

    @Test
    @Transactional
    @Sql("classpath:addRepairOrders.sql")
    public void findAll_ShouldReturnListOfRepairOrders_WhenCalled(){
        List<RepairOrder> actualResult = repairOrderRepository.findAll();
        List<RepairOrder> expectedResult = createRepairOrders();
        assertEquals(expectedResult, actualResult, "Execute method find all from repair orders");
    }

    public RepairOrder createRepairOrder(Long id) {
        Optional<Driver> driver = driverRepository.findById(1L);
        Optional<Car> car = carRepository.findById(1L);
        Optional<Trip> trip = tripRepository.findById(1L);

        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setId(id);
        repairOrder.setDriver(driver.get());
        repairOrder.setCar(car.get());
        repairOrder.setTrip(trip.get());
        repairOrder.setDescription("lalala");
        repairOrder.setRequestDate(LocalDate.now());
        repairOrder.setRepaired(false);
        return repairOrder;
    }

    public List<RepairOrder> createRepairOrders() {
        List<RepairOrder> repairOrders = new ArrayList<>();
        RepairOrder repairOrder = createRepairOrder(1L);
        repairOrders.add(repairOrder);
        RepairOrder repairOrder2 = createRepairOrder(2L);
        repairOrders.add(repairOrder2);
        return repairOrders;
    }

    @Test
    @Transactional
    @Sql("classpath:addRepairOrders.sql")
    public void findById_ShouldReturnRepairOrder_WhenCalled() {
        Optional<RepairOrder> actualResult = repairOrderRepository.findById(1L);
        RepairOrder expectedResult = createRepairOrder(1L);
        assertEquals(expectedResult, actualResult.get(), "Execute method find by id from repair orders");
    }
}
