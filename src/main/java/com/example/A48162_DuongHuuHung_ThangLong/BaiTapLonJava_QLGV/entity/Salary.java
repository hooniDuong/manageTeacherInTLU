package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "salary")
public class Salary {
    @Id
    @Column(name = "salary_id")
    private String salaryId;

    private Integer month;

    private Integer year;

    @Column(name = "total_hours")
    private Double totalHours;

    private Double bonus;

    private double deduction;

    @Column(name = "total_salary")
    private Double totalSalary;

    @Column(name = "create_date")
    private LocalDate createDate;

    @OneToMany
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
