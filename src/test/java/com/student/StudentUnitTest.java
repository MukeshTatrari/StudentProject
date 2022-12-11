package com.student;

import com.student.model.Student;
import com.student.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class StudentUnitTest {


    @Autowired
    private StudentService studentService;

    @Test
    public void getAllStudents() {
        List<Student> studentList = studentService.getStudents();
        assertNotNull(studentList);
        assertEquals(studentList.size(), 3);
    }

    @Test
    public void getStudentById() throws Exception {
        Student student = studentService.getStudentById(100);
        assertNotNull(student);
        assertEquals(student.getName(), "Mukesh");

    }
}
