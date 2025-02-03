package AutoBase.service.trip;

import AutoBase.AutoBaseApplication;
import AutoBase.convert.Converter;
import AutoBase.dao.*;
import AutoBase.dto.*;
import AutoBase.model.*;
import AutoBase.utils.TestConteinersConfiguration;
import org.checkerframework.checker.units.qual.A;
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
public class TripServiceImplTest {
    @Autowired
    private Converter converter;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestConteinersConfiguration testConteinersConfiguration;


    @Test
    @Transactional
    @Sql("classpath:schema2.sql")
    public void save_ShouldAddNewRowInTableTrips_WhenCalled() {
        List<Trip> expectedResult = new ArrayList<>();

        TripDTO tripDTO = new TripDTO();
        Trip trip = this.createTrip(tripDTO);
        expectedResult.add(trip);

        List<Trip> actualResult = tripRepository.findAll();
        assertEquals(expectedResult,
                actualResult,
                "Execute method save from trips");

    }

    public Trip createTrip(TripDTO tripDTO) {

        LocalDate start_date = LocalDate.now();
        LocalDate end_date = LocalDate.now().plusDays(6);
        Optional<Driver> driver = driverRepository.findById(1L);
        DriverDTO driverDTO = converter.convertDriverToDTO(driver.get());

        Optional<Car> car = carRepository.findById(1L);
        CarDTO carDTO = converter.convertCarToDTO(car.get());

        Optional<Order> order = orderRepository.findById(1L);
        OrderDTO orderDTO = converter.convertOrderToDTO(order.get());


        tripDTO.setId(1L);
        tripDTO.setStart_date(start_date);
        tripDTO.setEnd_date(end_date);
        tripDTO.setDriver(driverDTO);
        tripDTO.setCar(carDTO);
        tripDTO.setOrder(orderDTO);
        tripDTO.setPrice(1500);

        Trip trip = converter.convertToEntity(tripDTO);
        tripRepository.save(trip);
        return trip;

    }

    @Test
    @Transactional
    @Sql("classpath:addTrips.sql")
    public void findAll_ShouldReturnListOfTrips_WhenCalled() {
        List<Trip> expectedResult = new ArrayList<>();
        Trip trip = this.ExpectedTrip();

        expectedResult.add(trip);

        List<Trip> actualResult = tripRepository.findAll();
        assertEquals(expectedResult,
                actualResult,
                "Execute method findAll from trips");


    }

    @Test
    @Transactional
    @Sql("classpath:addTrips.sql")
    public void findById_ShouldReturnTripObject_WhenCalled() {
        Optional<Trip> actualResult = tripRepository.findById(1L);
        Trip expectedTrip = this.ExpectedTrip();
        assertEquals(expectedTrip, actualResult.get(), "Execute method findById from trips");
    }

    @Test
    @Transactional
    @Sql("classpath:addTrips.sql")
    public void findByDriverId_ShouldReturnTripObject_WhenCalled() {
        Trip actualResult = tripRepository.findByDriverId(1L);

        Trip expectedTrip = this.ExpectedTrip();
        assertEquals(expectedTrip, actualResult, "Execute method findByDriverId from trips");


    }

    public Trip ExpectedTrip(){
        Trip expectedTrip = new Trip();
        expectedTrip.setId(1L);
        expectedTrip.setStartDate(LocalDate.now());

        Optional<Driver> driver = driverRepository.findById(1L);
        Optional<Car> car = carRepository.findById(1L);
        Optional<Order> order = orderRepository.findById(1L);

        expectedTrip.setDriver(driver.get());
        expectedTrip.setCar(car.get());
        expectedTrip.setOrder(order.get());
        expectedTrip.setPrice(1500);
        return expectedTrip;
    }

}
