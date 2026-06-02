package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.salary;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.salary.SalaryRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.salary.SalaryResponseDTO;
import java.util.List;

/**
 * Service xử lý các nghiệp vụ liên quan đến LƯƠNG THƯỞNG (Salary)
 */
public interface SalaryService {
    // Lấy danh sách lịch sử trả lương của toàn trường
    List<SalaryResponseDTO> getAllSalaries();
    
    // Lấy Bảng lương của toàn trường trong 1 Tháng và 1 Năm cụ thể
    List<SalaryResponseDTO> getSalariesByMonthAndYear(Integer month, Integer year);
    
    // Logic phức tạp: Hệ thống tự động tính toán tổng lương (dựa vào số giờ dạy, phụ cấp, phạt) 
    // và chốt bảng lương cho Giáo viên đó.
    SalaryResponseDTO calculateAndSaveSalary(SalaryRequestDTO request);

    void deleteSalary(String id);

    // Tìm kiếm bảng lương
    List<SalaryResponseDTO> searchSalaries(String keyword);
}

