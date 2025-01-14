package AutoBase.dto;

import AutoBase.model.CargoType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;

    private CargoType cargoType;

    private int cargoWeight;

    private LocalDate requestData;

    private String destination;

    private DispatcherDTO dispatcher;

    private boolean hasTrip;
}
