package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "department_id")
    private String departmentID;

    private String name;

    private String description;

    @Column(name = "location_office")
    private String locationOffice;
}
