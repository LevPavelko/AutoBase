package AutoBase.service.car;

import AutoBase.dto.CarDTO;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Optional<CarDTO> findById(Long id);
    List<CarDTO> findAll();
    void update(CarDTO carDTO);
}
