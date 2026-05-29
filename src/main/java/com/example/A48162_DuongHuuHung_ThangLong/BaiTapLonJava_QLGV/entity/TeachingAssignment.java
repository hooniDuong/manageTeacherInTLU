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

    //Trạng thái phân công (VD: Đang dạy, Đã hoàn thành, Đã hủy)
    private String status;

    //@OneToMany -> @ManyToOne
    //Bảng TeachingAssignment là bảng phụ Nhiều-Nhiều.
    // Nhiều phân công có thể cùng trỏ về 1 lớp học, 1 giáo viên, 1 môn, 1 học kỳ.
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    // Lớp sinh viên tham gia khóa học này
    @ManyToOne
    @JoinColumn(name = "student_class_id")
    private StudentClass studentClass;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;
}
