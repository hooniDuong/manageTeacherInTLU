package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto;
import lombok.Data;

@Data
public class RoleResponseDTO {
    // MỤC ĐÍCH: Hiển thị danh sách Quyền (Ví dụ: để đổ vào giao diện phân quyền cho User)
    private String roleId;
    private String roleName;
}
