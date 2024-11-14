package com.ToDo.ToDo.controller;

import com.ToDo.ToDo.dto.SubjectDto;
import com.ToDo.ToDo.dto.GenericResponse;
import com.ToDo.ToDo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public GenericResponse createSubject(@RequestBody SubjectDto subjectDto) {
        return new GenericResponse(subjectService.createSubject(subjectDto));
    }

    @GetMapping
    public GenericResponse getAllSubject() {
        return new  GenericResponse(subjectService.getAllSubject());
    }

    @GetMapping("/{id}")
    public GenericResponse getSubjectById(@PathVariable String id) {
        return new GenericResponse(subjectService.getSubjectById(id));
    }

    @PutMapping("/{id}")
    public GenericResponse updateSubject(@PathVariable String id, @RequestBody SubjectDto subjectDTO) {
        return new GenericResponse(subjectService.updateSubject(id, subjectDTO));
    }

    @DeleteMapping("/{id}")
    public  GenericResponse deleteSubject(@PathVariable String id) {
        return new GenericResponse(subjectService.deleteSubject(id));
    }
}


