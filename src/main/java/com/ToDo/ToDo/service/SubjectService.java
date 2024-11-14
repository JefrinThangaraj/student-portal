package com.ToDo.ToDo.service;

import com.ToDo.ToDo.dto.SubjectDto;
import com.ToDo.ToDo.exception.CustomException;
import com.ToDo.ToDo.model.Subject;
import com.ToDo.ToDo.repository.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private static final String SUBJECT_NOT_FOUND = "Subject Not Found";
    private static final String SUBJECT_DELETED_SUCCESSFULLY = "Subject Deleted Successfully";
    private static final String SUBJECT_NAME_REQUIRED ="Enter your Subject Name";
    private static final String SUBJECT_CODE_REQUIRED ="Enter your Code";


    public SubjectService(SubjectRepository subjectRepository) {

        this.subjectRepository = subjectRepository;
    }

    public Subject createSubject(SubjectDto subjectDto) {

        Subject subject = new Subject();
        subject.setSubjectName(subjectDto.getSubjectName());
        subject.setSubjectCode(subjectDto.getSubjectCode());
        return subjectRepository.save(subject);
    }
    private void validationSubjectDto(SubjectDto subjectDto){

        if (subjectDto.getSubjectName() == null || subjectDto.getSubjectName().isEmpty()){
            throw new CustomException(SUBJECT_NAME_REQUIRED,HttpStatus.BAD_REQUEST);
        }
        if (subjectDto.getSubjectCode() == null || subjectDto.getSubjectCode().isEmpty()){
            throw new CustomException(SUBJECT_CODE_REQUIRED,HttpStatus.BAD_REQUEST);
        }
    }

    private SubjectDto convertDto (Subject newSubject){
        SubjectDto subjectDto=new SubjectDto();
        subjectDto.setId(newSubject.getId());
        subjectDto.setSubjectName(newSubject.getSubjectName());
        subjectDto.setSubjectCode(newSubject.getSubjectCode());
        return subjectDto;
    }
    public List<Subject> getAllSubject() {
        List<Subject> subjects= subjectRepository.findAll();
        if(subjects.isEmpty()){
            throw new CustomException(SUBJECT_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
        return subjects;
    }

    public SubjectDto getSubjectById(String id) {
        Subject subject = subjectRepository.findById(id).orElse(null);
        if (subject == null) {
            throw new CustomException(SUBJECT_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
        return convertDto(subject);
    }

    public Subject updateSubject(String id, SubjectDto subjectDto) {
        validationSubjectDto(subjectDto);
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new CustomException(SUBJECT_NOT_FOUND, HttpStatus.OK));

        subject.setSubjectName(subjectDto.getSubjectName());
        subject.setSubjectCode(subjectDto.getSubjectCode());

        return subjectRepository.save(subject);
    }

    public Subject deleteSubject(String id) {
        Optional<Subject> Subject = subjectRepository.findById(id);
        if (ObjectUtils.isEmpty(Subject)) {
            throw new CustomException(SUBJECT_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
        subjectRepository.deleteById(id);
        throw new CustomException(SUBJECT_DELETED_SUCCESSFULLY, HttpStatus.OK);
    }

    public Subject subjectIdPresent(String subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new CustomException(SUBJECT_NOT_FOUND, HttpStatus.NOT_FOUND));
        return subject;
    }
}
