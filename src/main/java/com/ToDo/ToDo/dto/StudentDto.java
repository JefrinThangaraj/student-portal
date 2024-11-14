package com.ToDo.ToDo.dto;

import com.ToDo.ToDo.model.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDto {
    private String id;
    private String fullName;
    private String collegeName;
    private String phoneNumber;
    private String email;
    private String password;
    private String confirmPassword;
    private Subject subject;
    private String subjectId;



}

