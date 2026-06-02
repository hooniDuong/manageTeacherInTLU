package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.department;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.department.DepartmentRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.department.DepartmentResponseDTO;

import java.util.List;

public interface DepartmentService {
    // Thêm khoa
    DepartmentResponseDTO addDepartment(DepartmentRequestDTO requestDTO);

    // Xem danh sách khoa
    List<DepartmentResponseDTO> getAllDepartments();

    void deleteDepartment(String id);

    // Tìm kiếm bộ môn theo tên
    List<DepartmentResponseDTO> searchDepartmentsByName(String name);
}
