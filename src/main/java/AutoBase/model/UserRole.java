package AutoBase.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "userrole")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column
    private  String role;

}
