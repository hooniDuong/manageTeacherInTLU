package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.user;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.user.UserRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.user.UserResponseDTO;
import java.util.List;

public interface UserService {
    // Lấy danh sách tất cả tài khoản
    List<UserResponseDTO> getAllUsers();
    
    // Tìm tài khoản theo ID
    UserResponseDTO getUserById(String id);
    
    // Lấy thông tin tài khoản dựa vào Tên đăng nhập (Rất quan trọng khi Đăng nhập)
    UserResponseDTO getUserByUsername(String username);
    
    // Đăng ký tài khoản mới / Cấp tài khoản mới
    UserResponseDTO createUser(UserRequestDTO request);
    
    // Đổi mật khẩu, khóa tài khoản...
    UserResponseDTO updateUser(String id, UserRequestDTO request);
    
    // Vô hiệu hóa hoặc xóa tài khoản
    void deleteUser(String id);

    // Tìm kiếm tài khoản
    List<UserResponseDTO> searchUsers(String keyword);
}

