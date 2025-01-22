package AutoBase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {

    private Long id;

    private DriverDTO driver;

    private CarDTO car;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;

    private int price;

    private OrderDTO order;


}
