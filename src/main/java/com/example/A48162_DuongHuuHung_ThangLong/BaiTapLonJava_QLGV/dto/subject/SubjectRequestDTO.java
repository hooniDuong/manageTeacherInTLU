package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.subject;
import lombok.Data;
@Data
public class SubjectRequestDTO {
    // Form thêm mới / Sửa môn học
    private String subjectId;
    private int credits;
    private String description;
    
    // Môn này do bộ môn nào quản lý? (Chỉ gửi ID từ Form)
    private String departmentId; 
}
