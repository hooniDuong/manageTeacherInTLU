package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.studentclass;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.studentclass.StudentClassRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.studentclass.StudentClassResponseDTO;
import java.util.List;

public interface StudentClassService {
    // Lấy danh sách toàn bộ Lớp sinh viên
    List<StudentClassResponseDTO> getAllClasses();
    
    // Tìm Lớp sinh viên theo ID
    StudentClassResponseDTO getClassById(String id);
    
    // Lọc danh sách Lớp sinh viên theo một Khoa cụ thể (VD: Lấy tất cả lớp của Khoa CNTT)
    List<StudentClassResponseDTO> getClassesByFacultyId(String facultyId);
    
    // Thêm mới Lớp sinh viên
    StudentClassResponseDTO createClass(StudentClassRequestDTO request);
    
    // Cập nhật thông tin Lớp sinh viên
    StudentClassResponseDTO updateClass(String id, StudentClassRequestDTO request);
    
    // Xóa Lớp sinh viên
    void deleteClass(String id);

    // Tìm kiếm lớp sinh viên theo từ khóa
    List<StudentClassResponseDTO> searchClasses(String keyword);
}

