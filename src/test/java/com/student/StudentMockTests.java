package com.student;

import com.student.model.Student;
import com.student.repository.StudentRepository;
import com.student.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class StudentMockTests {
    @InjectMocks
    StudentService studentService;
    @Mock
    StudentRepository repository;

    @Test
    public void testAddStudent() {

        List<Student> studentList = List.of(
                new Student(100, "Mukesh", "LPU", 10001, "COMPUTERs"),
                new Student(101, "Suresh", "IIM Kolkata", 10002, "MBA"),
                new Student(102, "Ramesh", "IIT Delhi", 10003, "Electronics")
        );

        Mockito.when(repository.findAll()).thenReturn(studentList);
        List<Student> students = studentService.getStudents();
        assertEquals(3, students.size());
    }

    @Test
    public void testgetStudentById() throws Exception {

        Student student = new Student(100, "Mukesh", "LPU", 10001, "COMPUTERs");
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(student));
        Student studentData = studentService.getStudentById(100);
        assertEquals("Mukesh", studentData.getName());
    }
}
