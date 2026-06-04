package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.classroom;
import lombok.Data;
@Data
public class ClassroomRequestDTO {
    //Form thêm mới / Sửa thông tin Phòng học
    private String classroomId;
    private String name;
    private Integer capacity;
    private String location;
}
