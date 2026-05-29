package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
    //Tìm role theo tên (Role: quản trị, giáo viên,...)
    Role findByRoleName (String roleName);
}
