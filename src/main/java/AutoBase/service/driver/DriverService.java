package AutoBase.service.driver;

import AutoBase.dto.CarDTO;
import AutoBase.dto.DriverDTO;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    Optional<DriverDTO> findById(Long id);
    List<DriverDTO> findAll();
    void update(DriverDTO driverDTO);
}
