package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.teachingLog;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.TeachingLog;
import java.util.List;

/**
 * Service xử lý các nghiệp vụ liên quan đến NHẬT KÝ GIẢNG DẠY / CHẤM CÔNG (TeachingLog)
 */
public interface TeachingLogService {
    // Lấy danh sách các buổi dạy của 1 Lịch phân công (Ví dụ: xem thầy A đã dạy được mấy buổi môn Java)
    List<TeachingLog> getLogsByAssignmentId(String assignmentId);
    
    List<TeachingLog> getAllLogs();
    
    // Xem chi tiết 1 buổi dạy (Dạy bài gì, vắng ai không)
    TeachingLog getLogById(String id);
    
    // Chấm công: Giáo viên (hoặc hệ thống) check-in thêm 1 buổi dạy vào lịch sử
    TeachingLog createLog(TeachingLog log);
    
    // Cập nhật thông tin buổi dạy (VD: báo nghỉ, dạy bù)
    TeachingLog updateLog(String id, TeachingLog log);
    
    // Xóa buổi dạy (xóa nhầm)
    void deleteLog(String id);

    // Tìm kiếm nhật ký giảng dạy theo từ khóa
    List<TeachingLog> searchLogs(String keyword);
}
