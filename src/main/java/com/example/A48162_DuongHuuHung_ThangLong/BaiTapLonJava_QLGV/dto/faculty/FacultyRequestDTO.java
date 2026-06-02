package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.faculty;
import lombok.Data;
@Data
public class FacultyRequestDTO {
    // Dùng làm Form Thêm mới / Sửa thông tin Khoa. Nơi Admin nhập liệu từ bàn phím.
    private String facultyId;
    private String name;
    private String description;
}
