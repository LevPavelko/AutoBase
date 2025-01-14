package AutoBase.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="experience_In_Years")
    private int experienceInYears;

    @Column(name = "is_busy")
    private boolean isBusy;


//    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Trip> trips;


}
