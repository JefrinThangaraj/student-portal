package com.ToDo.ToDo.controller;

import com.ToDo.ToDo.dto.GenericResponse;
import com.ToDo.ToDo.dto.StudentDto;
import com.ToDo.ToDo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public GenericResponse createStudent(@RequestBody StudentDto studentDto) {
        return new GenericResponse(studentService.createStudent(studentDto));
    }

    @GetMapping
    public GenericResponse getAllStudents() {
        return new  GenericResponse(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public GenericResponse getStudentById(@PathVariable String id) {
        return new GenericResponse(studentService.getStudentById(id));

    }

    @PutMapping("/{id}")
    public GenericResponse updateStudent(@PathVariable String id, @RequestBody StudentDto studentDTO) {
        return new GenericResponse(studentService.updateStudent(id, studentDTO));
    }

    @DeleteMapping("/{id}")
    public  GenericResponse deleteStudent(@PathVariable String id) {
        return new GenericResponse(studentService.deleteStudent(id));
    }
}
