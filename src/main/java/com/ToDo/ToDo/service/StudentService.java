package com.ToDo.ToDo.service;

import com.ToDo.ToDo.dto.StudentDto;
import com.ToDo.ToDo.exception.*;
import com.ToDo.ToDo.model.Student;
import com.ToDo.ToDo.model.Subject;
import com.ToDo.ToDo.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentsRepository;
    private final SubjectService subjectService;

    private static final String EMAIL_ALREADY_USED = "Email already in use";
    private static final String PHONE_NUMBER_ALREADY_USED = "Phone Number already in use";
    private static final String PASSWORDS_DO_NOT_MATCH = "Password do not match";
    private static final String UPDATE_STUDENT = "Student Id Not Found";
    private static final String DATA_NOT_FOUND = "Data not Found";
    private static final String STUDENT_DATA_DELETED_SUCCESSFULLY = "Student Data Deleted Successfully";
    private static final String STUDENT_DATA_NOT_FOUND = "Student Data Not Found";
    private static final String FULL_NAME_REQUIRED = "Enter Your Full Name";
    private static final String COLLEGE_NAME_REQUIRED = "Enter Your College Name";
    private static final String PHONE_NUMBER_REQUIRED = "Enter Your Phone Number";
    private static final String EMAIL_REQUIRED = "Enter Your Email";
    private static final String PASSWORD_REQUIRED = "Password Your Email";


    public StudentService(StudentRepository studentsRepository, SubjectService subjectService) {
        this.studentsRepository = studentsRepository;
        this.subjectService = subjectService;
    }

    public StudentDto createStudent(StudentDto studentDto) {

        if (studentsRepository.existsByEmail(studentDto.getEmail())) {
            throw new CustomException(EMAIL_ALREADY_USED, HttpStatus.BAD_REQUEST);
        }
        if (studentDto.getEmail() == null || studentDto.getEmail().isEmpty()) {
            throw new CustomException(EMAIL_REQUIRED, HttpStatus.BAD_REQUEST);
        }
        if (studentsRepository.existsByPhoneNumber(studentDto.getPhoneNumber())) {
            throw new CustomException(PHONE_NUMBER_ALREADY_USED, HttpStatus.BAD_REQUEST);
        }
        if (!studentDto.getPassword().equals(studentDto.getConfirmPassword())){
            throw new CustomException(PASSWORDS_DO_NOT_MATCH, HttpStatus.BAD_REQUEST);
        }
        if (studentDto.getPassword().isEmpty()){
            throw new CustomException(PASSWORD_REQUIRED, HttpStatus.BAD_REQUEST);
        }

        Subject subject = subjectService.subjectIdPresent(studentDto.getSubjectId());

        Student student = new Student();
        validationStudentDto(studentDto);
        studentToStudentDto(student, studentDto);
        student.setSubjectId(subject.getId());
        student.setSubject(subject);
        Student newStudent = studentsRepository.save(student);
        return convertDto(newStudent);
    }
    private void validationStudentDto(StudentDto studentDto){

        if (studentDto.getFullName() == null || studentDto.getFullName().isEmpty()) {
            throw new CustomException(FULL_NAME_REQUIRED, HttpStatus.BAD_REQUEST);
        }
        if (studentDto.getCollegeName() == null || studentDto.getCollegeName().isEmpty()) {
            throw new CustomException(COLLEGE_NAME_REQUIRED, HttpStatus.BAD_REQUEST);
        }
        if (studentDto.getPhoneNumber() == null || studentDto.getPhoneNumber().isEmpty()) {
            throw new CustomException(PHONE_NUMBER_REQUIRED, HttpStatus.BAD_REQUEST);
        }

    }

    private void studentToStudentDto(Student student, StudentDto studentDto) {
        student.setFullName(studentDto.getFullName());
        student.setCollegeName(studentDto.getCollegeName());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setEmail(studentDto.getEmail());
        student.setPassword(studentDto.getPassword());
        student.setConfirmPassword(studentDto.getConfirmPassword());
        }

    private StudentDto convertDto(Student newStudent){
        StudentDto studentDto=new StudentDto();
        studentDto.setId(newStudent.getId());
        studentDto.setFullName(newStudent.getFullName());
        studentDto.setCollegeName(newStudent.getCollegeName());
        studentDto.setPhoneNumber(newStudent.getPhoneNumber());
        studentDto.setEmail(newStudent.getEmail());
        studentDto.setPassword(newStudent.getPassword());
        studentDto.setConfirmPassword(newStudent.getConfirmPassword());
        studentDto.setSubjectId(newStudent.getSubject().getId());
        studentDto.setSubject(newStudent.getSubject());

        return studentDto;

    }

    public List<StudentDto> getAllStudents() {
        List<Student> students = studentsRepository.findAll();
        if (students.isEmpty()) {
            throw new CustomException(DATA_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
        return students.stream().map(this::convertDto).collect(Collectors.toList());
    }

    public StudentDto  getStudentById(String id) {
        Student student = studentsRepository.findById(id).orElse(null);
        if (student == null) {
            throw new CustomException(STUDENT_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
        return convertDto (student);
    }
    public StudentDto updateStudent(String id, StudentDto studentDto) {
        validationStudentDto(studentDto);
        Student student = studentsRepository.findById(id)
                .orElseThrow(() -> new CustomException(UPDATE_STUDENT, HttpStatus.OK));

        student.setFullName(studentDto.getFullName());
        student.setCollegeName(studentDto.getCollegeName());
        student.setPhoneNumber(studentDto.getPhoneNumber());

        Student updatedStudent = studentsRepository.save(student);
        return convertDto(updatedStudent);
    }
    public Student deleteStudent(String id) {
        Optional<Student> Student=studentsRepository.findById(id);
        if (ObjectUtils.isEmpty(Student)){
            throw new CustomException(DATA_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
        studentsRepository.deleteById(id);
        throw new CustomException(STUDENT_DATA_DELETED_SUCCESSFULLY, HttpStatus.OK);
    }

}
