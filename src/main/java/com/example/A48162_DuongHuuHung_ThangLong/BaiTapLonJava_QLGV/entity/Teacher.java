package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @Column(name = "teacher_id")
    private String teacherId;

    private String name;

    private String gender;

    private LocalDate birthday;

    private String phone;

    private String email;

    private String address;

    private String degree;

    private String position;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    private BigDecimal salary;

    private String status;

    private String avatar;

    //@OneToMany -> @ManyToOne (Nhiều giáo viên thuộc 1 Bộ môn)
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    //@OneToMany -> @OneToOne (Mỗi giáo viên ứng với duy nhất 1 tài khoản)
    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;
}
