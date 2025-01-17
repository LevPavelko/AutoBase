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

import static org.assertj.core.api.Assertions.assertThat;
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
