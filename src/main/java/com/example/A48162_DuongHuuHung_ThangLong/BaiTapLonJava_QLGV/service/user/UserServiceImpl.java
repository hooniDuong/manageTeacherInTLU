package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.user;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.user.UserRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.user.UserResponseDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Role;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.User;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.RoleRepository;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        // Xử lý lấy toàn bộ danh sách tài khoản
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> responseList = new ArrayList<>();
        for (User u : users) {
            responseList.add(mapToDTO(u));
        }
        return responseList;
    }

    @Override
    public UserResponseDTO getUserById(String id) {
        // Tìm tài khoản theo ID
        User u = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User với ID: " + id));
        return mapToDTO(u);
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        // Tìm tài khoản theo Tên đăng nhập
        User u = userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User với Username: " + username));
        return mapToDTO(u);
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {
        // Khởi tạo tài khoản mới
        User u = new User();
        u.setUserId(request.getUserId());
        u.setUsername(request.getUsername());
        u.setPassword(request.getPassword()); // Thực tế nên dùng BCrypt mã hóa ở đây
        u.setEmail(request.getEmail());
        u.setStatus(request.isStatus());
        u.setCreateDate(LocalDate.now()); // Ngày tạo tự động là ngày hôm nay

        // Gắn quyền cho tài khoản
        if (request.getRoleId() != null) {
            Role role = roleRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Quyền không hợp lệ"));
            u.setRole(role);
        }

        User saved = userRepository.save(u);
        return mapToDTO(saved);
    }

    @Override
    public UserResponseDTO updateUser(String id, UserRequestDTO request) {
        // Cập nhật thông tin tài khoản
        User u = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User với ID: " + id));
        
        u.setUsername(request.getUsername());
        // Chỉ đổi mật khẩu nếu có nhập mật khẩu mới
        if(request.getPassword() != null && !request.getPassword().isEmpty()) {
            u.setPassword(request.getPassword()); 
        }
        u.setEmail(request.getEmail());
        u.setStatus(request.isStatus());

        if (request.getRoleId() != null) {
            Role role = roleRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Quyền không hợp lệ"));
            u.setRole(role);
        }

        User updated = userRepository.save(u);
        return mapToDTO(updated);
    }

    @Override
    public void deleteUser(String id) {
        // Xóa tài khoản
        User u = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User với ID: " + id));
        userRepository.delete(u);
    }

    // Hàm chuyển đổi Entity sang DTO
    private UserResponseDTO mapToDTO(User u) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(u.getUserId());
        dto.setUsername(u.getUsername());
        dto.setEmail(u.getEmail());
        dto.setStatus(u.isStatus());
        dto.setCreateDate(u.getCreateDate());
        if (u.getRole() != null) {
            dto.setRoleName(u.getRole().getRoleName());
        }
        return dto;
    }

    @Override
    public List<UserResponseDTO> searchUsers(String keyword) {
        // Tìm kiếm các tài khoản theo username hoặc email chứa từ khóa
        List<User> users = userRepository.findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
        List<UserResponseDTO> responseList = new ArrayList<>();
        for (User u : users) {
            responseList.add(mapToDTO(u));
        }
        return responseList;
    }
}

