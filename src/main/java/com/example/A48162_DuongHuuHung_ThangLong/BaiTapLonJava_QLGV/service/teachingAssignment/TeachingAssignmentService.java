package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.teachingAssignment;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teachingAssignment.TeachingAssignmentRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teachingAssignment.TeachingAssignmentResponseDTO;
import java.util.List;

/**
 * Service xử lý các nghiệp vụ liên quan đến PHÂN CÔNG GIẢNG DẠY (TeachingAssignment)
 */
public interface TeachingAssignmentService {
    // Lấy toàn bộ danh sách phân công
    List<TeachingAssignmentResponseDTO> getAllAssignments();
    
    // Xem chi tiết 1 lịch phân công
    TeachingAssignmentResponseDTO getAssignmentById(String id);
    
    // Lấy Thời khóa biểu của một Giáo viên cụ thể trong một Học kỳ cụ thể
    List<TeachingAssignmentResponseDTO> getAssignmentsByTeacherAndSemester(String teacherId, String semesterId);
    
    // Tạo mới một lịch phân công (Xếp lịch dạy)
    TeachingAssignmentResponseDTO createAssignment(TeachingAssignmentRequestDTO request);
    
    // Sửa lịch dạy (Đổi phòng, đổi giờ...)
    TeachingAssignmentResponseDTO updateAssignment(String id, TeachingAssignmentRequestDTO request);
    
    // Hủy phân công giảng dạy
    void deleteAssignment(String id);

    // Tìm kiếm phân công giảng dạy theo từ khóa
    List<TeachingAssignmentResponseDTO> searchAssignments(String keyword);
}

