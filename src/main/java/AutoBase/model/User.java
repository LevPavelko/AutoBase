package AutoBase.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column
    private  String firstName;

    @Column
    private  String lastName;

    @Column
    private int age;

    @Column
    private String email;

    @Column
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id")
    private UserRole role;

}
