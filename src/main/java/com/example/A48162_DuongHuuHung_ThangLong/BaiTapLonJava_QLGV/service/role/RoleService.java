package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.role;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.RoleRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.RoleResponseDTO;

import java.util.List;

/**
 * Service xử lý các nghiệp vụ liên quan đến QUYỀN TRUY CẬP (Role)
 */
public interface RoleService {
    // Lấy danh sách tất cả các Quyền (Admin, Teacher, Manager...)
    List<RoleResponseDTO> getAllRoles();
    
    // Lấy thông tin một Quyền cụ thể
    RoleResponseDTO getRoleById(String id);
    
    // Tạo một Quyền mới trong hệ thống
    RoleResponseDTO createRole(RoleRequestDTO request);
    
    // Xóa một Quyền (Thường ít dùng vì Quyền hay được fix cứng)
    void deleteRole(String id);

    // Tìm kiếm quyền theo tên
    List<RoleResponseDTO> searchRolesByName(String name);
}

