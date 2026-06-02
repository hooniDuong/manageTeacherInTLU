package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    // Dùng để lấy thông tin khi user đăng nhập (chỉ tìm theo username)
    // Sau đó Controller sẽ tự so sánh password trong Java
    // Lý do: không dùng findByUsernameAndPassword vì nếu DB có bản ghi trùng
    //        sẽ bị lỗi NonUniqueResultException
    Optional<User> findFirstByUsername(String username);

    //Kiểm tra xem tên user đã tồn tại hay chưa (tránh bị trùng lặp tài khoản)
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    // Tìm kiếm tài khoản theo tên đăng nhập hoặc email
    List<User> findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(String username, String email);
}
