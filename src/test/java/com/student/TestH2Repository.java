package com.student;

import com.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Student, Integer> {
}
