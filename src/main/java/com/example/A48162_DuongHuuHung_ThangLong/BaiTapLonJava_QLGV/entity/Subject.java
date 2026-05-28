package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "subject_id")
    private String subject_id;

    private int credits; //Số tín chỉ

    private String description;

    @OneToMany
    @JoinColumn(name = "department_id")
    private Department departments;
}
