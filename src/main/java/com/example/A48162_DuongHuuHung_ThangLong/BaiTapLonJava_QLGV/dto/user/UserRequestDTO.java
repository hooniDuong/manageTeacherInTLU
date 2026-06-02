package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.user;
import lombok.Data;
@Data
public class UserRequestDTO {
    // Dùng cho form Đăng ký tài khoản hoặc Admin tạo tài khoản.
    private String userId;
    private String username;
    
    // Ở form đăng ký, dĩ nhiên người dùng phải nhập Mật khẩu.
    private String password; 
    private String email;
    private boolean status;
    
    // Cấp quyền gì cho user này (Admin, Giáo viên...)?
    private String roleId; 
}
