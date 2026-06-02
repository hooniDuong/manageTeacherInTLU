package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.contract;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class ContractResponseDTO {
    // MỤC ĐÍCH: Hiển thị danh sách Hợp đồng
    private String contractId;
    private String contractType;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal baseSalary;
    private String status;
    
    // Hiển thị tên Giáo viên cho dễ đọc thay vì chỉ hiện ID
    private String teacherName; 
}
