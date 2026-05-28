package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "classroom")
public class Classroom {
    @Id
    @Column(name = "classroom_id")
    private String classroomId;

    private String name;

    private String building;

    private int capacity;
}
