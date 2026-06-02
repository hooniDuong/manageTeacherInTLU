package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teachinglog;
import lombok.Data;
import java.time.LocalDate;
@Data
public class TeachingLogResponseDTO {
    // MỤC ĐÍCH: Hiển thị danh sách Nhật ký giảng dạy
    private String logId;
    private LocalDate teachingDate;
    private Integer actualHours;
    private String content;
    private String note;
    
    // Hiển thị thêm thông tin môn học và lớp cho rõ ràng
    private String subjectName;
    private String studentClassName;
}
