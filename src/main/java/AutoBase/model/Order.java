package AutoBase.model;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo_type")
    private CargoType cargoType;

    @Column(name = "cargo_weight")
    private int cargoWeight;

    @Column(name = "request_data")
    private LocalDate requestData;

    @Column
    private String Destination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispatcher_id")
    private Dispatcher dispatcher;

    @Column(name = "has_trip")
    private boolean hasTrip;

}
