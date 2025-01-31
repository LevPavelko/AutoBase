package AutoBase.service.car;

import AutoBase.AutoBaseApplication;
import AutoBase.dao.CarRepository;
import AutoBase.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.assertEquals;

@SpringBootTest
@Sql(scripts = {"classpath:schema.sql"})
@ContextConfiguration(classes = AutoBaseApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
public class CarServiceImplTest {
    @Autowired
    private CarRepository carRepository;

    @Test
    @Transactional
    @Sql("classpath:addCars.sql")
    public void findAll_ShouldReturnAllCars_WhenCalled() {
        List<Car> actualResult = carRepository.findAll();
        List<Car> expectedResult = createCars();
        assertEquals(expectedResult,
                actualResult,
                "Execute method findAll from cars");

    }

    @Test
    @Transactional
    @Sql("classpath:addCars.sql")
    public void findById_ShouldReturnCar_WhenCalled() {
        Optional<Car> actualResult = carRepository.findById(1L);
        Car expectedResult = createCar();
        assertEquals(expectedResult, actualResult.get(), "Execute method findById from car");
    }

    public List<Car> createCars() {
        List<Car> cars = new ArrayList<>();

        Car car1 = new Car();
        car1.setId(1L);
        car1.setType(CarType.REFRIGERATED_VAN);
        car1.setFree(true);
        car1.setBroken(false);
        car1.setCapacity(2);
        cars.add(car1);

        Car car2 = new Car();
        car2.setId(2L);
        car2.setType(CarType.REFRIGERATED_VAN);
        car2.setFree(true);
        car2.setBroken(false);
        car2.setCapacity(2);
        cars.add(car2);
         return cars;

    }

    public Car createCar() {
        Car car = new Car();
        car.setId(1L);
        car.setType(CarType.REFRIGERATED_VAN);
        car.setFree(true);
        car.setBroken(false);
        car.setCapacity(2);
        return car;
    }

 }

