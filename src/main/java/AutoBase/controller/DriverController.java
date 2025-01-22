package AutoBase.controller;

import AutoBase.dto.DriverDTO;
import AutoBase.dto.UserDTO;
import AutoBase.dto.UserRoleDTO;
import AutoBase.model.User;
import AutoBase.service.driver.DriverService;
import AutoBase.service.driver.DriverServiceImpl;
import AutoBase.service.role.RoleServiceImpl;
import AutoBase.service.user_service.UserServiceImpl;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class DriverController {
    @Autowired
    private DriverServiceImpl driverService;

    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private SpringDataWebAutoConfiguration springDataWebAutoConfiguration;

    @RequestMapping("/drivers")
    public String drivers(Model model) {
        List<DriverDTO> drivers = driverService.findAll();
        model.addAttribute("drivers", drivers);
        return "drivers";
    }

    @RequestMapping("/addDriver")
    public String addDriverView(Model model) {
        DriverDTO driver = new DriverDTO();
        model.addAttribute("driverDTO", driver);
        return "addDriver";
    }

    @PostMapping("/AddDriverForm")
    public String addDriverForm(DriverDTO driverDTO, Model model) {
        if(driverDTO.getUserDTO().getFirstName() == "" || driverDTO.getUserDTO().getLastName() == "" ||
                driverDTO.getUserDTO().getAge() == 0 || driverDTO.getExperienceInYears() == 0) {

            model.addAttribute("message", "Fill in all fields");
            model.addAttribute("driverDTO", driverDTO);
            return "addDriver";
        }
        if(!isValidEmail(driverDTO.getUserDTO().getEmail())) {
            model.addAttribute("message", "Not valid email");
            model.addAttribute("driverDTO", driverDTO);

            return "addDriver";
        }
        try{
            Optional<UserRoleDTO> userRoleDTO = roleService.findById(1L);

            UserDTO userDTO  = new UserDTO();
            userDTO.setFirstName(driverDTO.getUserDTO().getFirstName());
            userDTO.setLastName(driverDTO.getUserDTO().getLastName());
            userDTO.setEmail(driverDTO.getUserDTO().getEmail());
            userDTO.setPassword("$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu");
            userDTO.setAge(driverDTO.getUserDTO().getAge());
            userDTO.setRoleDTO(userRoleDTO.get());
            Long userId = userServiceImpl.save(userDTO);
            userDTO = userServiceImpl.findById(userId).get();

            DriverDTO newDriver = new DriverDTO();
            newDriver.setUserDTO(userDTO);
            newDriver.setBusy(false);
            newDriver.setExperienceInYears(driverDTO.getExperienceInYears());
            driverService.save(newDriver);

            return "redirect:/drivers";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "redirect:/500";
        }
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@[\\w-]+(\\.[\\w-]+)*\\.[a-z]{2,}$");
    }
}

