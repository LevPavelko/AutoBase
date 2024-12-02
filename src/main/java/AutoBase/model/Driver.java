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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private int age;

    @Column
    private String email;

    @Column(name="experience_In_Years")
    private int experienceInYears;

    @Column(name = "is_busy")
    private boolean isBusy;

    @Column
    private String password;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trip> trips;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Driver driver = (Driver) object;
        return age == driver.age && experienceInYears == driver.experienceInYears && isBusy == driver.isBusy && Objects.equals(id, driver.id) && Objects.equals(firstName, driver.firstName) && Objects.equals(lastName, driver.lastName) && Objects.equals(email, driver.email) && Objects.equals(password, driver.password) && Objects.equals(trips, driver.trips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, email, experienceInYears, isBusy, password, trips);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", experienceInYears=" + experienceInYears +
                ", isBusy=" + isBusy +
                ", password='" + password + '\'' +
                ", trips=" + trips +
                '}';
    }
}
