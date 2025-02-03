package AutoBase.service.driver;
import AutoBase.*;

import AutoBase.convert.Converter;
import AutoBase.dao.*;
import AutoBase.dto.*;
import AutoBase.model.*;
import AutoBase.utils.TestConteinersConfiguration;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(scripts = {"classpath:schema.sql"})
@ContextConfiguration(classes = AutoBaseApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
public class DriverServiceImplTest {
    @Autowired
    private TestConteinersConfiguration testConteinersConfiguration;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private Converter converter;

    @Test
    @Transactional
    public void save_ShouldAddNewRowInTableDrivers_WhenCalled() {
        List<Driver> expectedResult = new ArrayList<>();

        DriverDTO driverDTO = new DriverDTO();
        Driver driver = this.createDriver(driverDTO);
        expectedResult.add(driver);

        List<Driver> actualResult = driverRepository.findAll();
        assertEquals(expectedResult,
                actualResult,
                "Execute method save from drivers");

    }

    @Test
    @Transactional
    public void update_ShouldUpdateRowInTableDrivers_WhenCalled() {
        List<Driver> expectedResult = new ArrayList<>();

        DriverDTO driverDTO = new DriverDTO();
        Driver driver = this.createDriver(driverDTO);
        expectedResult.add(driver);

        List<Driver> actualResult = driverRepository.findAll();
        assertEquals(expectedResult,
                actualResult,
                "Execute method save from drivers");

        driverDTO.setBusy(true);
        driver = converter.convertToEntity(driverDTO);
        driverRepository.save(driver);

        expectedResult.clear();
        expectedResult.add(driver);

        actualResult = driverRepository.findAll();
        assertEquals(expectedResult,
                actualResult,
                "Execute method update from drivers");

    }

    @Test
    @Transactional
    @Sql("classpath:addDrivers.sql")
    public void findAll_ShouldReturnDrivers_WhenCalled() {
        List<Driver> actualResult = driverRepository.findAll();
        List<Driver> expectedResult = createDrivers();
        assertEquals(expectedResult,actualResult, "Execute method findAll from drivers");
    }

    @Test
    @Transactional
    public void findById_ShouldReturnDriver_WhenCalled() {
        Driver expectedResult = createDriver(new DriverDTO());
        Optional<Driver> actualResult = driverRepository.findById(1L);

        assertEquals(expectedResult,actualResult.get(), "Execute method findAll from drivers");

    }

    public List<Driver> createDrivers(){
        List<Driver> expectedResult = new ArrayList<>();
        Optional<User> user1 = userRepository.findById(1L);
        Optional<User> user2 = userRepository.findById(2L);

        Driver driver1 = new Driver();
        driver1.setId(1L);
        driver1.setUser(user1.get());
        driver1.setExperienceInYears(7);
        driver1.setBusy(false);
        expectedResult.add(driver1);

        Driver driver2 = new Driver();
        driver2.setId(2L);
        driver2.setUser(user2.get());
        driver2.setExperienceInYears(7);
        driver2.setBusy(false);
        expectedResult.add(driver2);

        return expectedResult;
    }

    public Driver createDriver(DriverDTO driverDTO) {

        UserDTO userDTO = new UserDTO();
        UserRoleDTO roleDTO = new UserRoleDTO();

        roleDTO.setId(1L);
        roleDTO.setRole("ROLE_DRIVER");
        UserRole userRole = userRoleRepository.save(converter.convertToEntity(roleDTO));

        userDTO.setId(1L);
        userDTO.setFirstName("Michael");
        userDTO.setLastName("Sidovor");
        userDTO.setEmail("michael.sidorov@gmail.com");
        userDTO.setPassword("password");
        userDTO.setAge(39);
        userDTO.setRoleDTO(roleDTO);

        User user = userRepository.save(converter.convertToEntity(userDTO));


        driverDTO.setId(1L);
        driverDTO.setBusy(false);
        driverDTO.setExperienceInYears(7);
        driverDTO.setUserDTO(userDTO);
        Driver driver = converter.convertToEntity(driverDTO);
        driverRepository.save(driver);

        return driver;
    }
}
