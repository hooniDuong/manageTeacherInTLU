package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.semester;
import lombok.Data;
import java.time.LocalDate;
@Data
public class SemesterResponseDTO {
    // MỤC ĐÍCH: Hiển thị danh sách Học kỳ
    private String semesterId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}
