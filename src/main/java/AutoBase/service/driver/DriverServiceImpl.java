package AutoBase.service.driver;

import AutoBase.convert.Converter;
import AutoBase.dao.DriverRepository;
import AutoBase.dto.DriverDTO;
import AutoBase.model.Driver;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private Converter converter;

    @Override
    public List<DriverDTO> findAll() {
        List<Driver> drivers = driverRepository.findAll();
        return drivers.stream()
                .map(converter::convertDriverToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DriverDTO> findById(Long id) {
        Optional<Driver> driver = driverRepository.findById(id);
        return driver.map(converter::convertDriverToDTO);
    }

    @Override
    public void update(DriverDTO dto) {
        Driver driver = converter.convertToEntity(dto);
        driverRepository.save(driver);
    }
}
