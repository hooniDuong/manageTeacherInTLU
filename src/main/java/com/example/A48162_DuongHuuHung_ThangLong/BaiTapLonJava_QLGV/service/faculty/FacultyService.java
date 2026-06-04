package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.faculty;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.faculty.FacultyResponseDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.faculty.FacultyRequestDTO;
import java.util.List;

public interface FacultyService {
    // Lấy danh sách toàn bộ các Khoa trong trường
    List<FacultyResponseDTO> getAllFaculties();
    
    // Tìm kiếm thông tin chi tiết của 1 Khoa dựa vào ID
    FacultyResponseDTO getFacultyById(String id);
    
    // Thêm mới một Khoa vào hệ thống (Nhận dữ liệu từ form thêm mới)
    FacultyResponseDTO createFaculty(FacultyRequestDTO request);
    
    // Cập nhật thông tin Khoa đã tồn tại
    FacultyResponseDTO updateFaculty(String id, FacultyRequestDTO request);
    
    // Xóa một Khoa khỏi hệ thống
    void deleteFaculty(String id);

    // Tìm kiếm Khoa theo tên
    List<FacultyResponseDTO> searchFacultiesByName(String name);
}
