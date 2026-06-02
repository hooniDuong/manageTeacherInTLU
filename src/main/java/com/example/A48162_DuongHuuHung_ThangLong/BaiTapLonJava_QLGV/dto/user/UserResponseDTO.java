package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.user;
import lombok.Data;
import java.time.LocalDate;
@Data
public class UserResponseDTO {
    // MỤC ĐÍCH: Hiển thị danh sách Tài khoản cho Admin xem.
    private String userId;
    private String username;
    
    // BẢO MẬT: Không được phép có trường 'password' ở đây. 
    // Việc này ngăn hacker chặn bắt API để lấy trộm mật khẩu.
    private String email;
    private boolean status;
    private LocalDate createDate;
    
    // Hiển thị tên quyền để đọc (VD: Quản trị viên).
    private String roleName; 
}
