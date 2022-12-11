package co.com.poli.usersservice.persistance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "El apellido no puede estar vacío")
    @Column(name = "last_name")
    private String lastname;

}
