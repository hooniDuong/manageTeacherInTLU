package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.semester;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Semester;
import java.util.List;

public interface SemesterService {
    // Lấy danh sách các học kỳ
    List<Semester> getAllSemesters();
    
    // Xem chi tiết thời gian bắt đầu, kết thúc của 1 học kỳ
    Semester getSemesterById(String id);
    
    // Khởi tạo một Học kỳ mới (VD: Học kỳ 1 Năm 2024-2025)
    Semester createSemester(Semester semester);
    
    // Sửa thông tin học kỳ
    Semester updateSemester(String id, Semester semester);
    
    // Xóa học kỳ
    void deleteSemester(String id);

    // Tìm kiếm học kỳ theo tên
    List<Semester> searchSemestersByName(String name);
}
