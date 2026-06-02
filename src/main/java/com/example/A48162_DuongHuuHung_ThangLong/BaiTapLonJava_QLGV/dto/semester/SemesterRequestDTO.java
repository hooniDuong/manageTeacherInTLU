package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.semester;
import lombok.Data;
import java.time.LocalDate;
@Data
public class SemesterRequestDTO {
    // MỤC ĐÍCH: Form khai báo Học kỳ mới
    private String semesterId;
    private String name;
    private Integer year;
    private LocalDate startDate;
    private LocalDate endDate;
}
