package AutoBase.service.car;

import AutoBase.convert.Converter;
import AutoBase.dao.CarRepository;
import AutoBase.dto.CarDTO;
import AutoBase.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService{
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private Converter converter;

    @Override
    public List<CarDTO> findAll() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(converter::convertCarToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CarDTO> findById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        return car.map(converter::convertCarToDTO);
    }

    @Override
    public void update(CarDTO carDTO) {
        Car car = converter.convertToEntity(carDTO);
        carRepository.save(car);
    }
}
