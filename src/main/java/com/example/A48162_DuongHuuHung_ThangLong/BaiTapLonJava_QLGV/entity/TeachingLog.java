package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "teaching_log")
public class TeachingLog {
    @Id
    @Column(name = "log_id")
    private String logId;

    @Column(name = "teaching_date")
    private LocalDate teachingDate;

    @Column(name = "hours_taught")
    private Double hoursTaught; // Số giờ dạy thực tế của buổi đó

    private String topic; // Nội dung bài giảng hôm đó

    private String status; // Trạng thái: Đã dạy, Báo nghỉ, Dạy bù, ...

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private TeachingAssignment assignment;
}
