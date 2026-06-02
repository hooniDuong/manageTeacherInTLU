package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//Không cần chức năng gì
public interface FacultyRepository extends JpaRepository<Faculty, String> {
    // Tìm kiếm khoa theo tên khoa (không phân biệt chữ hoa thường)
    List<Faculty> findByNameContainingIgnoreCase(String name);
}
