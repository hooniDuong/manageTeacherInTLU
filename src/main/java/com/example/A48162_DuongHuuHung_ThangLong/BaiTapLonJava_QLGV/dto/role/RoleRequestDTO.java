package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto;
import lombok.Data;

@Data
public class RoleRequestDTO {
    // MỤC ĐÍCH: Dùng cho Form Thêm mới quyền (Admin, Teacher...)
    private String roleId;
    private String roleName;
}
