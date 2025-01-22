package AutoBase.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
public class RepairOrderDTO {

    private Long id;
    private DriverDTO driver;
    private CarDTO car;
    private String description;
    private TripDTO trip;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate requestDate;
    private boolean isRepaired;
}
