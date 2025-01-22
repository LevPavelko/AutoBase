package AutoBase.convert;


import AutoBase.dto.*;
import AutoBase.model.*;
import org.springframework.stereotype.Service;

@Service
public class Converter {
    public CarDTO convertCarToDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setCarType(car.getType());
        carDTO.setFree(car.isFree());
        carDTO.setCapacity(car.getCapacity());
        carDTO.setBroken(car.isBroken());
        return carDTO;
    }

    public OrderDTO convertOrderToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCargoType(order.getCargoType());
        orderDTO.setCargoWeight(order.getCargoWeight());
        orderDTO.setDestination(order.getDestination());
        orderDTO.setRequestData(order.getRequestData());
        orderDTO.setDispatcher(convertDispatcherToDTO(order.getDispatcher()));
        orderDTO.setHasTrip(order.isHasTrip());
        return orderDTO;
    }//DONE

    public DriverDTO convertDriverToDTO(Driver driver) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(driver.getId());
        driverDTO.setUserDTO(convertUserToDTO(driver.getUser()));
        driverDTO.setExperienceInYears(driver.getExperienceInYears());
        driverDTO.setBusy(driver.isBusy());
        return driverDTO;
    }// DONEs

    public DispatcherDTO convertDispatcherToDTO(Dispatcher dispatcher) {
        DispatcherDTO dispatcherDTO = new DispatcherDTO();
        dispatcherDTO.setId(dispatcher.getId());
        dispatcherDTO.setUserDTO(convertUserToDTO(dispatcher.getUser()));
        return dispatcherDTO;
    }//DONE

    public UserDTO convertUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAge(user.getAge());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoleDTO(convertUserRoleToDTO(user.getRole()));
        return userDTO;
    } //DONE

    public UserRoleDTO convertUserRoleToDTO(UserRole userRole) {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setId(userRole.getId());
        userRoleDTO.setRole(userRole.getRole());
        return userRoleDTO;
    } //DONE

    public TripDTO convertTripToDTO(Trip trip) {
        TripDTO tripDTO = new TripDTO();
        if(trip == null){
            return null;
        }
        tripDTO.setId(trip.getId());
        tripDTO.setDriver(convertDriverToDTO(trip.getDriver()));
        tripDTO.setCar(convertCarToDTO(trip.getCar()));
        tripDTO.setOrder(convertOrderToDTO(trip.getOrder()));
        tripDTO.setPrice(trip.getPrice());
        tripDTO.setEnd_date(trip.getEndDate());
        tripDTO.setStart_date(trip.getStartDate());
        return tripDTO;
    }

    public RepairOrderDTO convertRepairOrderToDTO(RepairOrder repairOrder) {
        RepairOrderDTO repairOrderDTO = new RepairOrderDTO();
        repairOrderDTO.setId(repairOrder.getId());
        repairOrderDTO.setDriver(convertDriverToDTO(repairOrder.getDriver()));
        repairOrderDTO.setCar(convertCarToDTO(repairOrder.getCar()));
        repairOrderDTO.setDescription(repairOrder.getDescription());
        repairOrderDTO.setTrip(convertTripToDTO(repairOrder.getTrip()));
        repairOrderDTO.setRequestDate(repairOrder.getRequestDate());
        repairOrderDTO.setRepaired(repairOrder.isRepaired());
        return repairOrderDTO;
    } //DONE

    public Trip convertToEntity(TripDTO tripDTO) {
        Trip trip = new Trip();
        trip.setId(tripDTO.getId());
        trip.setStartDate(tripDTO.getStart_date());
        trip.setEndDate(tripDTO.getEnd_date());
        trip.setPrice(tripDTO.getPrice());
        trip.setCar(convertToEntity(tripDTO.getCar()));
        trip.setOrder(convertToEntity(tripDTO.getOrder()));
        trip.setDriver(convertToEntity(tripDTO.getDriver()));
        return trip;

    } //DONE

    public Driver convertToEntity(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setId(driverDTO.getId());
        driver.setUser(convertToEntity(driverDTO.getUserDTO()));
        driver.setExperienceInYears(driverDTO.getExperienceInYears());
        driver.setBusy(driverDTO.isBusy());
        return driver;
    } //DONE

    public Car convertToEntity(CarDTO carDTO) {
        Car car = new Car();
        car.setId(carDTO.getId());
        car.setType(carDTO.getCarType());
        car.setFree(carDTO.isFree());
        car.setCapacity(carDTO.getCapacity());
        car.setBroken(carDTO.isBroken());
        return car;

    } //DONE

    public Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setCargoType(orderDTO.getCargoType());
        order.setCargoWeight(orderDTO.getCargoWeight());
        order.setDestination(orderDTO.getDestination());
        order.setRequestData(orderDTO.getRequestData());
        order.setDispatcher(convertToEntity(orderDTO.getDispatcher()));
        order.setHasTrip(orderDTO.isHasTrip());

        return order;
    } //DONE

    public Dispatcher convertToEntity(DispatcherDTO dispatcherDTO) {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setId(dispatcherDTO.getId());
        dispatcher.setUser(convertToEntity(dispatcherDTO.getUserDTO()));
        return dispatcher;
    } //DONE

    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setAge(userDTO.getAge());
        user.setPassword(userDTO.getPassword());
        user.setRole(convertToEntity(userDTO.getRoleDTO()));
        return user;
    } //DONE

    public UserRole convertToEntity(UserRoleDTO userRoleDTO) {
        UserRole userRole = new UserRole();
        userRole.setId(userRoleDTO.getId());
        userRole.setRole(userRoleDTO.getRole());
        return userRole;
    } //DONE

    public RepairOrder convertToEntity(RepairOrderDTO repairOrderDTO) {
        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setId(repairOrderDTO.getId());
        repairOrder.setDriver(convertToEntity(repairOrderDTO.getDriver()));
        repairOrder.setCar(convertToEntity(repairOrderDTO.getCar()));
        repairOrder.setTrip(convertToEntity(repairOrderDTO.getTrip()));
        repairOrder.setRequestDate(repairOrderDTO.getRequestDate());
        repairOrder.setDescription(repairOrderDTO.getDescription());
        repairOrder.setRepaired(repairOrderDTO.isRepaired());
        return repairOrder;
    }
}
