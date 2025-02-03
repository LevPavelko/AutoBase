package AutoBase.controller;

import AutoBase.dto.DriverDTO;
import AutoBase.dto.UserDTO;
import AutoBase.dto.UserRoleDTO;
import AutoBase.service.driver.DriverServiceImpl;
import AutoBase.service.role.RoleService;
import AutoBase.service.role.RoleServiceImpl;
import AutoBase.service.user_service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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


    @MockBean
    private UserServiceImpl userServiceImpl;

    @MockBean
    private RoleServiceImpl roleService;


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
        roleDTO.setRole("ROLE_DRIVER");
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

    @Test
    public void testAddDriverForm_Success() throws Exception {

        UserRoleDTO roleDTO = new UserRoleDTO();
        roleDTO.setId(1L);
        roleDTO.setRole("ROLE_DRIVER");
        when(roleService.findById(1L)).thenReturn(Optional.of(roleDTO));


        when(userServiceImpl.save(any(UserDTO.class))).thenReturn(1L);
        when(userServiceImpl.findById(1L)).thenReturn(Optional.of(new UserDTO()));


        doNothing().when(driverService).save(any(DriverDTO.class));

        mockMvc.perform(post("/AddDriverForm").with(user("popov.alex@gmail.com").password("123").roles("DISPATCHER"))
                        .param("userDTO.firstName", "John")
                        .param("userDTO.lastName", "Doe")
                        .param("userDTO.email", "john@doe.com")
                        .param("userDTO.age", "32")
                        .param("experienceInYears", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/drivers"));

        verify(driverService, times(1)).save(any(DriverDTO.class));
    }

    @Test
    public void testAddDriverForm_MissingFields_ShouldReturnError() throws Exception {
        mockMvc.perform(post("/AddDriverForm").with(user("popov.alex@gmail.com").password("123").roles("DISPATCHER"))
                        .param("userDTO.firstName", "")
                        .param("userDTO.lastName", "")
                        .param("userDTO.email", "john@doe.com")
                        .param("userDTO.age", "0")
                        .param("experienceInYears", "0"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "Fill in all fields"))
                .andExpect(view().name("addDriver"));
    }

}
