package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.studentclass;
import lombok.Data;
@Data
public class StudentClassResponseDTO {
    // Dùng để hiển thị Bảng danh sách Lớp sinh viên cho người xem.
    private String classId;
    private String name;
    private String major;
    private Integer enrollmentYear;
    
    // Trả về tên Khoa (VD: Khoa CNTT) cho người dùng dễ đọc, thay vì trả mã Khoa khô khan.
    private String facultyName; 
}
