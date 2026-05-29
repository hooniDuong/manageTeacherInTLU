package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "department") //Bộ môn: Trong CNTT có nhiều bộ môn như HTTT, KHMT,...
public class Department {
    @Id
    @Column(name = "department_id")
    private String departmentId;

    private String name;

    private String description;

    @Column(name = "location_office")
    private String locationOffice;

    //Một Khoa (Faculty) có nhiều Bộ môn (Department)
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
}
