package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.teacher;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teacher.TeacherRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teacher.TeacherResponseDTO;
import java.util.List;

/**
 * Service xử lý các nghiệp vụ liên quan đến GIÁO VIÊN (Teacher)
 * Đây là Service cốt lõi của hệ thống.
 */
public interface TeacherService {
    // Lấy danh sách toàn bộ Giáo viên
    List<TeacherResponseDTO> getAllTeachers();
    
    // Xem hồ sơ chi tiết của 1 Giáo viên
    TeacherResponseDTO getTeacherById(String id);
    
    // Lọc danh sách Giáo viên theo Bộ môn (VD: Tìm các thầy cô thuộc Bộ môn KTPM)
    List<TeacherResponseDTO> getTeachersByDepartment(String departmentId);
    
    // Tiếp nhận hồ sơ Giáo viên mới vào trường
    TeacherResponseDTO createTeacher(TeacherRequestDTO request);
    
    // Cập nhật thông tin Giáo viên (Lên chức, đổi SĐT...)
    TeacherResponseDTO updateTeacher(String id, TeacherRequestDTO request);
    
    // Xóa hồ sơ Giáo viên (Hoặc chuyển trạng thái sang Đã nghỉ hưu)
    void deleteTeacher(String id);

    // Tìm kiếm Giáo viên theo tên
    List<TeacherResponseDTO> searchTeachersByName(String name);
}

