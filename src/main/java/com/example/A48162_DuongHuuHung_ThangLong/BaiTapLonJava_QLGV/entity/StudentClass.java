package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "student_class")
public class StudentClass {
    @Id
    @Column(name = "class_id")
    private String classId;

    private String name;

    private String major; // Chuyên ngành

    @Column(name = "enrollment_year")
    private Integer enrollmentYear; // Khóa nhập học (Ví dụ: 2023)

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
}
