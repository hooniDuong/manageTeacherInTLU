package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.subject;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.subject.SubjectRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.subject.SubjectResponseDTO;
import java.util.List;

/**
 * Service xử lý các nghiệp vụ liên quan đến MÔN HỌC (Subject)
 */
public interface SubjectService {
    // Danh sách toàn bộ môn học
    List<SubjectResponseDTO> getAllSubjects();
    
    // Xem chi tiết môn học
    SubjectResponseDTO getSubjectById(String id);
    
    // Lấy danh sách các môn học do một Bộ môn chịu trách nhiệm giảng dạy
    List<SubjectResponseDTO> getSubjectsByDepartment(String departmentId);
    
    // Thêm môn học mới
    SubjectResponseDTO createSubject(SubjectRequestDTO request);
    
    // Cập nhật số tín chỉ, tên môn học...
    SubjectResponseDTO updateSubject(String id, SubjectRequestDTO request);
    
    // Xóa môn học (ít khi xóa, thường đánh dấu ngừng giảng dạy)
    void deleteSubject(String id);

    // Tìm kiếm môn học theo mô tả/tên
    List<SubjectResponseDTO> searchSubjectsByDescription(String description);
}

