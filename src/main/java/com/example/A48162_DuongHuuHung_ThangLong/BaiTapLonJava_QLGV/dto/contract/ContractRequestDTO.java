package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.contract;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class ContractRequestDTO {
    // MỤC ĐÍCH: Form ký Hợp đồng lao động mới với Giáo viên
    private String contractId;
    private String contractType;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal baseSalary;
    private String status;
    
    // Gửi lên ID của giáo viên sẽ ký hợp đồng này
    private String teacherId; 
}
