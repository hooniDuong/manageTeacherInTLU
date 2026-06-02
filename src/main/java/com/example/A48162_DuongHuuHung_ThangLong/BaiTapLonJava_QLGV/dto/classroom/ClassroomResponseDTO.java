package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.classroom;
import lombok.Data;
@Data
public class ClassroomResponseDTO {
    // MỤC ĐÍCH: Hiển thị danh sách Phòng học
    private String classroomId;
    private String name;
    private Integer capacity;
    private String location;
}
