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

import javax.servlet.http.HttpSession;
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
                .filter(carDTO -> !carDTO.isBroken())
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
            model.addAttribute("message", "Fill in all fields");
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

    @RequestMapping("/trips")
    public String TripsView(Model model) {
        List<TripDTO> trips = tripService.findAll();
        model.addAttribute("trips", trips);
        return "trips";
    }

    @RequestMapping("/updateTrip")
    public String updateTripView(@RequestParam("tripId") Long tripId, Model model) {

        TripDTO tripDTO;
        CarDTO carDTO;
        Optional<TripDTO> tripDTOOptional = tripService.findById(tripId);
        tripDTO = tripDTOOptional.get();
        tripDTO.setId(tripId);


        Optional<CarDTO> carDTOOptional = carService.findById(tripId);
        carDTO = carDTOOptional.get();

        List<CarDTO> cars = carService.findAll();
        System.out.println(availableCars);
        availableCars = cars.stream()
                .filter(CarDTO::isFree)
                .filter(car -> !car.isBroken())
                .collect(Collectors.toList());

        availableCars.add(carDTO);

        model.addAttribute("cars", availableCars);
        model.addAttribute("tripDTO", tripDTO);

        return "/updateTrip";
    }

    @PostMapping("/updateTripForm")
    public String updateTripForm(TripDTO tripDTO, Long carId,
                                 Long orderId, Long driverId, Model model) {
        try{
            if (Objects.isNull(orderId) || tripDTO.getStart_date() == null
                    || carId == null || driverId == null || tripDTO.getPrice() == 0 ) {
                model.addAttribute("tripDTO", tripDTO);
                model.addAttribute("message", "Fill in all fields");
                return "forward:/updateTrip?tripId=" + tripDTO.getId();
            }
            else {
                Optional<CarDTO> optionalCarDTO = carService.findById(carId);
                Optional<DriverDTO> optionalDriverDTO = driverService.findById(driverId);
                Optional<OrderDTO> optionalOrderDTO = orderService.findById(orderId);

                if(tripDTO.getEnd_date() != null){
                    optionalCarDTO.get().setFree(true);
                    carService.update(optionalCarDTO.get());
                    optionalDriverDTO.get().setBusy(false);
                    driverService.update(optionalDriverDTO.get());
                    optionalCarDTO.get().setFree(true);
                    carService.update(optionalCarDTO.get());
                }
                tripDTO.setCar(optionalCarDTO.get());
                tripDTO.setDriver(optionalDriverDTO.get());
                tripDTO.setOrder(optionalOrderDTO.get());

                tripService.update(tripDTO);
                return "redirect:/trips";
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            return "redirect:/500";
        }


    }

    @RequestMapping("/activeTrip")
    public String activeTrip(Model model, HttpSession session) {
        Long driverId = (Long) session.getAttribute("driverId");
        TripDTO trip = tripService.findByDriverId(driverId);
        model.addAttribute("tripDTO", trip);
        return "/activeTrip";

    }


    @RequestMapping("/tripsHistory")
    public String tripsHistoryForDriver(Model model, HttpSession session) {
        Long driverId = (Long) session.getAttribute("driverId");
        List<TripDTO> trips = tripService.findAllDriverId(driverId);
        System.out.println("trips:" + trips);
        model.addAttribute("trips", trips);
        return "tripsHistory";
    }


}
