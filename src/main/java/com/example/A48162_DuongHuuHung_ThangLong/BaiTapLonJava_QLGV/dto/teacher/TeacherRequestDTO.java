package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teacher;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class TeacherRequestDTO {
    // Dùng làm Form Thêm mới / Cập nhật Hồ sơ Giáo viên.
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
    
    // Chỉ cần mã số Bộ môn và mã Tài khoản.
    private String departmentId; 
    private String userId;
}
