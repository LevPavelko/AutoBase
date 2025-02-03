package AutoBase.controller;

import AutoBase.dto.CarDTO;
import AutoBase.dto.DriverDTO;
import AutoBase.dto.RepairOrderDTO;
import AutoBase.dto.TripDTO;
import AutoBase.service.car.CarServiceImpl;
import AutoBase.service.driver.DriverServiceImpl;
import AutoBase.service.repair_order_service.RepairOrderService;
import AutoBase.service.repair_order_service.RepairOrderServiceImpl;
import AutoBase.service.trip.TripServiceImpl;
import org.apache.catalina.LifecycleState;
import org.flywaydb.core.internal.command.DbRepair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class RepairOrderController {
    @Autowired
    private RepairOrderServiceImpl repairOrderService;

    @Autowired
    private TripServiceImpl tripService;


    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private DriverServiceImpl driverService;

    @RequestMapping("/repairOrder")
    public String RepairOrder(@RequestParam("tripId") Long tripId, @RequestParam("carId") Long carId,
                              HttpSession session, Model model) {
        Long driverId = (Long) session.getAttribute("driverId");
        RepairOrderDTO repairOrderDTO  = new RepairOrderDTO();
        model.addAttribute("driverId", driverId);
        model.addAttribute("tripId", tripId);
        model.addAttribute("carId", carId);
        model.addAttribute("repairOrder", repairOrderDTO);
        return "/repairOrder";

    }
    @PostMapping("/RepairOrderForm")
    public String RepairOrderForm(RepairOrderDTO repairOrderDTO, Long tripId, Long carId,Long driverId, Model model) {
        Optional<TripDTO> tripDTOOptional = tripService.findById(tripId);
        Optional<CarDTO> carDTOOptional = carService.findById(carId);
        Optional<DriverDTO> driverDTOOptional = driverService.findById(driverId);

        carDTOOptional.get().setBroken(true);
        carService.update(carDTOOptional.get());

        LocalDate date = LocalDate.now();
        repairOrderDTO.setTrip(tripDTOOptional.get());
        repairOrderDTO.setCar(carDTOOptional.get());
        repairOrderDTO.setDriver(driverDTOOptional.get());
        repairOrderDTO.setRepaired(false);
        repairOrderDTO.setRequestDate(date);
        repairOrderService.save(repairOrderDTO);

        return "/home";
    }

    @RequestMapping("/repairOrders")
    public String RepairOrders(Model model) {
        List<RepairOrderDTO> repairOrderDTOList = repairOrderService.findAll();

        List<RepairOrderDTO> repairedOrders = repairOrderDTOList.stream()
                .filter(RepairOrderDTO -> !RepairOrderDTO.isRepaired())
                .collect(Collectors.toList());

        model.addAttribute("repairOrders", repairedOrders);
        return "repairOrders";
    }

    @GetMapping("/updateRepairOrder")
    public String update(@RequestParam("id") Long id) {
        Optional<RepairOrderDTO> repairOrderDTO = repairOrderService.findById(id);
        repairOrderDTO.get().setRepaired(true);
        repairOrderService.update(repairOrderDTO.get());
        CarDTO carDTO = repairOrderDTO.get().getCar();
        carDTO.setBroken(false);
        carService.update(carDTO);
        return "/home";
    }
}
