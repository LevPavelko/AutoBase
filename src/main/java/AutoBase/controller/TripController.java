package AutoBase.controller;

import AutoBase.dao.CarRepository;
import AutoBase.dto.CarDTO;
import AutoBase.dto.DriverDTO;
import AutoBase.dto.OrderDTO;
import AutoBase.dto.TripDTO;
import AutoBase.service.car.CarServiceImpl;
import AutoBase.service.driver.DriverServiceImpl;
import AutoBase.service.order.OrderServiceImpl;
import AutoBase.service.trip.TripServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class TripController {
    @Autowired
    private TripServiceImpl tripService;

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private DriverServiceImpl driverService;

    @Autowired
    private OrderServiceImpl orderService;

    public List<DriverDTO> availableDrivers = new ArrayList<>();
    public List<CarDTO> availableCars = new ArrayList<>();
    @Autowired
    private CarRepository carRepository;

    @RequestMapping("/AddTrip")
    public String AddTripView(@RequestParam("orderId") Long orderId, Model model) {
        TripDTO tripDTO = new TripDTO();


        List<DriverDTO> drivers = driverService.findAll();
         availableDrivers = drivers.stream()
                .filter(driver -> !driver.isBusy())
                .collect(Collectors.toList());

        List<CarDTO> cars = carService.findAll();
        availableCars = cars.stream()
                .filter(CarDTO::isFree)
                .collect(Collectors.toList());

        model.addAttribute("orderId", orderId);
        model.addAttribute("cars", availableCars);
        model.addAttribute("tripDTO", tripDTO);
        model.addAttribute("drivers", availableDrivers);
        return "AddTrip";
    }


    @PostMapping("/AddTripForm")
    public String addTripForm(Long orderId, TripDTO tripDTO, Long carId, Long driverId, Model model) {

        if (Objects.isNull(orderId) || Objects.isNull(tripDTO)
                || carId == null || driverId == null || tripDTO.getPrice() == 0 ) {

            model.addAttribute("tripDTO", tripDTO);
            model.addAttribute("cars", availableCars);
            model.addAttribute("drivers", availableDrivers);
            model.addAttribute("orderId", orderId);
            model.addAttribute("message", "Please fix the errors in the form before submitting.");
            return "AddTrip";
        }

        try {
            Optional<CarDTO> optionalCarDTO = carService.findById(carId); //maybe i should make different functions like "ChangeCarStatus" and for other too
            CarDTO carDTO = optionalCarDTO.get();
            carDTO.setFree(false);
            carService.update(carDTO);

            Optional<OrderDTO> optionalOrderDTO = orderService.findById(orderId);
            OrderDTO orderDTO = optionalOrderDTO.get();
            orderDTO.setHasTrip(true);
            orderService.update(orderDTO);

            Optional<DriverDTO> optionalDriverDTO = driverService.findById(driverId);
            DriverDTO driverDTO = optionalDriverDTO.get();
            driverDTO.setBusy(true);
            driverService.update(driverDTO);

            tripDTO.setCar(carDTO);
            tripDTO.setOrder(orderDTO);
            tripDTO.setDriver(driverDTO);
            tripService.save(tripDTO);

            return "redirect:/orders";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/500";
        }
    }

}
