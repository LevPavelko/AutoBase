package AutoBase.service.trip;

import AutoBase.dto.OrderDTO;
import AutoBase.dto.TripDTO;

import java.util.List;
import java.util.Optional;

public interface TripService {
       void save(TripDTO tripDTO);
       void update(TripDTO tripDTO);
        TripDTO findByDriverId(Long driverId);
        List<TripDTO> findAllDriverId(Long driverId);
       List<TripDTO> findAll();
        Optional<TripDTO> findById(Long id);
}
