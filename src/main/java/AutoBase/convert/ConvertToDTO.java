//package AutoBase.convert;
//
//import AutoBase.dto.DispatcherDTO;
//import AutoBase.dto.OrderDTO;
//import AutoBase.dto.UserDTO;
//import AutoBase.dto.UserRoleDTO;
//import AutoBase.model.Dispatcher;
//import AutoBase.model.Order;
//import AutoBase.model.User;
//import AutoBase.model.UserRole;
//
//public class ConvertToDTO {
//    public OrderDTO convertOrderToDTO(Order order) {
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setId(order.getId());
//        orderDTO.setCargoType(order.getCargoType());
//        orderDTO.setCargoWeight(order.getCargoWeight());
//        orderDTO.setDestination(order.getDestination());
//        orderDTO.setDispatcher();
//        return orderDTO;
//    }
//    public DispatcherDTO convertDispatcherToDTO(Dispatcher dispatcher) {
//        DispatcherDTO dispatcherDTO = new DispatcherDTO();
//        dispatcherDTO.setId(dispatcher.getId());
//        dispatcherDTO.setUserDTO();
//        return dispatcherDTO;
//    }
//    public UserDTO convertUserToDTO(User user) {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setFirstName(user.getFirstName());
//        userDTO.setLastName(user.getLastName());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setAge(user.getAge());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setRoleDTO(user.getRole(convertUserRoleToDTO(user.getRole())));
//        return userDTO;
//    }
//
//    public UserRoleDTO convertUserRoleToDTO(UserRole userRole) {
//        UserRoleDTO userRoleDTO = new UserRoleDTO();
//        userRoleDTO.setId(userRole.getId());
//        userRoleDTO.setRole(userRole.getRole());
//        return userRoleDTO;
//    }
//}
