package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "subject_id")
    private String subjectId;

    private int credits; //Số tín chỉ

    private String description;

//    @OneToMany -> @ManyToOne (Nhiều môn học thuộc 1 Bộ môn)
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
