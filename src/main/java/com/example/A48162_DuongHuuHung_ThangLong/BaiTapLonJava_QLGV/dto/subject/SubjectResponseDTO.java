package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.subject;
import lombok.Data;
@Data
public class SubjectResponseDTO {
    // Bảng danh sách môn học
    private String subjectId;
    private int credits;
    private String description;
    
    // Hiển thị tên Bộ môn
    private String departmentName; 
}
