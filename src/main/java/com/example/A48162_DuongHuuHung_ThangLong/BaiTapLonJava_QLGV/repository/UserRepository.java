package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    //Dùng để lấy thông tin khi user đăng nhập
    User findByUsername(String username);

    //Kiểm tra xem tên user đã tồn tại hay chưa (tránh bị trùng lặp tài khoản)
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
