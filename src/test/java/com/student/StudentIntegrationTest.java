package com.student;

import com.student.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";
    private static RestTemplate restTemplate;

    @Autowired
    private TestH2Repository h2Repository;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/students");
    }


    @Test
    @Sql(statements = "INSERT INTO STUDENT_TBL (STUDENT_ID,name, college, ROLL_NO,course) " +
            "VALUES (100, 'Mukesh', 'LPU', 10001, 'COMPUTERS')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM STUDENT_TBL WHERE STUDENT_ID='100'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetProducts() {
        List<Student> students = restTemplate.getForObject(baseUrl, List.class);
        assertEquals(1, students.size());
        assertEquals(1, h2Repository.findAll().size());
    }

    @Test
    @Sql(statements = "INSERT INTO STUDENT_TBL (STUDENT_ID,name, college, ROLL_NO,course) " +
            "VALUES (100, 'Mukesh', 'LPU', 10001, 'COMPUTERS')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM STUDENT_TBL WHERE STUDENT_ID='100'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testFindProductById() {
        Student student = restTemplate.getForObject(baseUrl + "/100", Student.class, 1);
        assertAll(
                () -> assertNotNull(student),
                () -> assertEquals(100, student.getStudentId()),
                () -> assertEquals("Mukesh", student.getName())
        );

    }
}
