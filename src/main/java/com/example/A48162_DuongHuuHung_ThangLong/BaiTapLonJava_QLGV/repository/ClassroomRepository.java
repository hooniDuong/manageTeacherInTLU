package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, String> {
    // Tìm kiếm phòng học theo tên phòng (không phân biệt chữ hoa, chữ thường)
    List<Classroom> findByNameContainingIgnoreCase(String name);
}
