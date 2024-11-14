package com.ToDo.ToDo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@NoArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")

    private String id;
    private String fullName;
    private String collegeName;
    private String phoneNumber;
    private String email;
    private String password;
    private String confirmPassword;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;


    @Getter
    @Transient
    private String subjectId;

}
