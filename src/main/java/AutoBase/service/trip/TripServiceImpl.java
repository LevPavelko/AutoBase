package AutoBase.service.trip;

import AutoBase.convert.Converter;
import AutoBase.dao.TripRepository;
import AutoBase.dto.TripDTO;
import AutoBase.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private Converter converter;

    @Override
    public void save(TripDTO tripDTO) {
       Trip trip = converter.convertToEntity(tripDTO);
       tripRepository.save(trip);

    }
}
