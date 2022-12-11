package com.student.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STUDENT_TBL")
public class Student {

    @Id
    @Column(name = "STUDENT_ID")
    private Integer studentId;
    private String name;
    private String college;
    @Column(name="ROLL_NO")
    private Integer rollNo;
    private String course;

}

