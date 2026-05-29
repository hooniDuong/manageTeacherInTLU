package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "contract") // Hợp động của giáo viên
public class Contract {
    @Id
    @Column(name = "contract_id")
    private String contractId;

    @Column(name = "contract_type")
    private String contractType; // Loại hợp đồng

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "base_salary")
    private BigDecimal baseSalary; // Mức lương cơ bản (hoặc lương theo giờ nếu là thỉnh giảng)

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
