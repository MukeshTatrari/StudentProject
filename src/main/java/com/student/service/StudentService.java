package com.student.service;

import com.student.model.Student;
import com.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    List<Student> studentsList = new ArrayList<Student>();

    @Autowired
    private StudentRepository repository;

    public StudentService() {
        studentsList.addAll(
                List.of(
                        new Student(100, "Mukesh", "LPU", 10001, "COMPUTERs"),
                        new Student(101, "Suresh", "IIM Kolkata", 10002, "MBA"),
                        new Student(102, "Ramesh", "IIT Delhi", 10003, "Electronics")
                )
        );
    }

    public List<Student> getStudents() {
        return repository.findAll();
//        return studentsList;
    }

    public Student getStudentById(Integer studentId) throws Exception {
        return repository.findById(studentId).orElse(null);
    }

    public Student addStudent(Student student) {
        return repository.save(student);
    }

    public String deleteStudent(Student student) {
        repository.delete(student);
        return "Successfully deleted student :: ";
    }

}
