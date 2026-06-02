package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.studentclass;
import lombok.Data;
@Data
public class StudentClassRequestDTO {
    // MỤC ĐÍCH: Dùng cho Form Thêm mới Lớp sinh viên.
    private String classId;
    private String name;
    private String major;
    private Integer enrollmentYear;
    
    // Khi điền form, người dùng chỉ việc chọn Khoa từ một cái Dropdown list (lấy ID), 
    // không cần gửi nguyên cục đối tượng Faculty.
    private String facultyId; 
}
