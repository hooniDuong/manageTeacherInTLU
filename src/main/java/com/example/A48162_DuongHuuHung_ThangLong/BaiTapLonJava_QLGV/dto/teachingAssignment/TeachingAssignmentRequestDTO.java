package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teachingAssignment;
import lombok.Data;
import java.time.LocalDate;
@Data
public class TeachingAssignmentRequestDTO {
    // Form phân công Giảng dạy. (Chọn các mục từ Dropdown Combobox).
    private String assignmentId;
    private Integer hours;
    private String schedule;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    
    // Chỉ gửi các ID từ form (Ví dụ: ID=GV01, ID=JAVA01, ID=P301)
    private String classroomId;
    private String studentClassId;
    private String teacherId;
    private String subjectId;
    private String semesterId;
}
