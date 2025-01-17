package AutoBase.service.trip;

import AutoBase.dto.OrderDTO;
import AutoBase.dto.TripDTO;

import java.util.List;
import java.util.Optional;

public interface TripService {
       void save(TripDTO tripDTO);
       void update(TripDTO tripDTO);
//    void delete(TripDTO tripDTO);
       List<TripDTO> findAll();
        Optional<TripDTO> findById(Long id);
}
