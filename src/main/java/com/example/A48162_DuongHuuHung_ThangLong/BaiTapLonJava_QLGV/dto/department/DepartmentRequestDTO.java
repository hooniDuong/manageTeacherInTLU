package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.department;

import lombok.Data;

@Data
public class DepartmentRequestDTO {
    //điền trên Giao diện Web
    private String departmentId;
    private String name;
    private String description;
    private String locationOffice;

    private String facultyId;
}
