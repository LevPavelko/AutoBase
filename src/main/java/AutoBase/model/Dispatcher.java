package AutoBase.model;


import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "Dispatchers")
public class Dispatcher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column
    private  String firstName;

    @Column
    private  String lastName; //maybe i should make more field for dispatcher

    @Column
    private String email;

    @Column
    private String password;

    @OneToMany(mappedBy = "dispatcher", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Dispatcher that = (Dispatcher) object;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, orders);
    }

}
