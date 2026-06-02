package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teacher;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TeacherResponseDTO {
    // Xem danh sách Giáo viên (Dành cho Sinh viên hoặc Nhân viên phòng đào tạo).
    private String teacherId;
    private String name;
    private String gender;
    private LocalDate birthday;
    private String phone;
    private String email;
    private String address;
    private String degree;
    private String position;
    private LocalDate hireDate;
    private BigDecimal salary;
    private String status;
    private String avatar;
    
    // Hiện tên bộ môn.
    private String departmentName;
    private String departmentId;
    private String userId;
}
