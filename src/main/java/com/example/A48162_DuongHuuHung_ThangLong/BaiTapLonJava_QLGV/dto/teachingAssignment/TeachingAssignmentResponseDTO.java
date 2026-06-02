package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teachingAssignment;
import lombok.Data;
@Data
public class TeachingAssignmentResponseDTO {
    // MỤC ĐÍCH: Hiển thị Thời khóa biểu (Đã tổng hợp thành chữ rõ ràng).
    private String assignmentId;
    private Integer hours;
    private String schedule;
    private String status;
    
    // Đã convert ID sang tên hiển thị
    private String classroomName;   // VD: Phòng 301
    private String studentClassName;// VD: Lớp CNTT1-K62
    private String teacherName;     // VD: Thầy Nguyễn Văn A
    private String subjectName;     // VD: Lập trình Java
    private String semesterName;    // VD: Học kỳ 1 (2024)
}
