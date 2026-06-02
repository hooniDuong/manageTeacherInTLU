package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.faculty;
import lombok.Data;
@Data
public class FacultyResponseDTO {
    // Dùng để trả dữ liệu ra Bảng danh sách Khoa trên giao diện.
    private String facultyId;
    private String name;
    private String description;
}
