package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "faculty") // Khoa
public class Faculty {
    @Id
    @Column(name = "faculty_id")
    private String facultyId;

    private String name;

    private String description;
}
