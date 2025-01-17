package AutoBase.controller;

import AutoBase.dto.DriverDTO;
import AutoBase.dto.UserDTO;
import AutoBase.dto.UserRoleDTO;
import AutoBase.service.driver.DriverServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DriverControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private DriverServiceImpl driverService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testGetDrivers_ShouldReturnAllDrivers_WhenRequestSendFromDispatcher() throws Exception {
        List<DriverDTO> driverDTOList = new ArrayList<>();
        DriverDTO driverDTO = new DriverDTO();
        UserDTO userDTO = new UserDTO();
        UserRoleDTO roleDTO = new UserRoleDTO();

        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        roleDTO.setRole("ROLE_DISPATCHER");
        userDTO.setEmail("john@doe.com");
        userDTO.setPassword("password");
        userDTO.setAge(32);
        userDTO.setRoleDTO(roleDTO);
        driverDTO.setUserDTO(userDTO);

        driverDTOList.add(driverDTO);

        when(driverService.findAll()).thenReturn(driverDTOList);
        mockMvc.perform(get("/drivers").with(user("popov.alex@gmail.com").password("123").roles("DISPATCHER")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("drivers", driverService.findAll()))
                .andExpect(view().name("drivers"));

    }




}
