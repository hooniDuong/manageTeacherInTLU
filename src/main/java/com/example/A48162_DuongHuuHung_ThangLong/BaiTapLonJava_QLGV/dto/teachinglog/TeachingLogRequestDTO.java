package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teachinglog;
import lombok.Data;
import java.time.LocalDate;
@Data
public class TeachingLogRequestDTO {
    // MỤC ĐÍCH: Form chấm công / Ghi lại nhật ký giảng dạy từng buổi
    private String logId;
    private LocalDate teachingDate;
    private Integer actualHours;
    private String content; // Nội dung bài dạy hôm đó
    private String note;
    
    // Liên kết với ID của Lịch phân công (assignment) nào
    private String assignmentId; 
}
