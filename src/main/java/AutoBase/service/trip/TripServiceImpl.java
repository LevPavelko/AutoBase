package AutoBase.service.trip;

import AutoBase.convert.Converter;
import AutoBase.dao.TripRepository;
import AutoBase.dto.OrderDTO;
import AutoBase.dto.TripDTO;
import AutoBase.model.Order;
import AutoBase.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<TripDTO> findAll() {
        List<Trip> trips = tripRepository.findAll();
        return trips.stream()
                .map(converter::convertTripToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TripDTO> findById(Long id) {
        Optional<Trip> trip = tripRepository.findById(id);
        return trip.map(converter::convertTripToDTO);
    }

    @Override
    public void update(TripDTO tripDTO) {
        Trip trip = converter.convertToEntity(tripDTO);
        tripRepository.save(trip);
    }

    @Override
    public TripDTO findByDriverId(Long id) {
        Trip trip = tripRepository.findByDriverId(id);
        TripDTO dto = converter.convertTripToDTO(trip);
        return dto;
    }

    @Override
    public List<TripDTO> findAllDriverId(Long id) {
        List<Trip> trips = tripRepository.findAllDriverId(id);
        return trips.stream()
                .map(converter::convertTripToDTO)
                .collect(Collectors.toList());

    }
}
