package com.ToDo.ToDo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class Login  {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;

    @Enumerated(EnumType.STRING)
    private Role role;

}