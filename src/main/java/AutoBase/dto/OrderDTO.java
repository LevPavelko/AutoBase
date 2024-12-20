package AutoBase.dto;

import AutoBase.model.CargoType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;

    private CargoType cargoType;

    private int cargoWeight;

    private Date requestData;

    private String destination;

    private Long dispatcher_id;
}
