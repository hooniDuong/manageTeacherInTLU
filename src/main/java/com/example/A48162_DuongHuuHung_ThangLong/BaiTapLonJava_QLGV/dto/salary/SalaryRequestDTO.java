package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.salary;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class SalaryRequestDTO {
    // Dùng cho Form Nhập lương / Chốt lương tháng
    private String salaryId;
    private Integer month;
    private Integer year;
    private Double totalHours;
    private BigDecimal bonus;
    private BigDecimal deduction;
    private BigDecimal totalSalary;
    
    // Thuộc về Giáo viên nào
    private String teacherId; 
}
