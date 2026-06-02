package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterRepository extends JpaRepository<Semester, String> {
    //Xem các học kì trong cùng một năm học (Integer year)
    List<Semester> findByYear(Integer year);

    // Tìm kiếm học kỳ theo tên học kỳ (không phân biệt chữ hoa, chữ thường)
    List<Semester> findByNameContainingIgnoreCase(String name);
}
