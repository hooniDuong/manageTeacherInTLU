package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.department;

import lombok.Data;

@Data
public class DepartmentResponseDTO {
    // Khách hàng xem trên Giao diện Web
    private String departmentId;
    private String name;
    private String locationOffice;
    private String description;

    private String facultyName;
}
