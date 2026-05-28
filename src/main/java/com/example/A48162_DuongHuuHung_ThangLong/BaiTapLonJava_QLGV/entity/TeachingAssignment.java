package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "teaching_assignment")
public class TeachingAssignment {
    @Id
    @Column(name = "assignment_id")
    private String assignmentId;

    private Integer hours;

    private String schedule;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @OneToMany
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany
    @JoinColumn(name = "semester_id")
    private Semester semester;
}
