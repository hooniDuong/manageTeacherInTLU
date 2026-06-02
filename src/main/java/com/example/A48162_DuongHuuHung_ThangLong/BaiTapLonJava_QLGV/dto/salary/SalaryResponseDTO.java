package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.salary;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class SalaryResponseDTO {
    // Hiển thị Bảng Lương cho Admin hoặc Giáo viên xem
    private String salaryId;
    private Integer month;
    private Integer year;
    private Double totalHours;
    private BigDecimal bonus;
    private BigDecimal deduction;
    private BigDecimal totalSalary;
    private LocalDate createDate;
    
    // Hiện Tên Giáo viên thay vì Mã ID
    private String teacherName; 
}
