package AutoBase.dto;

import AutoBase.model.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private CarType carType;
    private boolean isFree;
    private long capacity;
    private boolean isBroken;
}
