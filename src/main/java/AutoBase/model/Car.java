package AutoBase.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column (name = "type")
    private CarType type;

    @Column(name = "is_free")
    private boolean isFree;

    @Column
    private long capacity;

    @Column(name = "is_broken")
    private boolean isBroken;

}
