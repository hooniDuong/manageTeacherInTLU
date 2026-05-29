package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, String> {
    //Tìm bảng lương cụ thể của một giáo viên trong 1 tháng/năm cụ thể
    Salary findByTeacher_TeacherIdAndMonthAndYear(String teacherId, String month, Integer year);

    //Lấy toàn bộ danh sách lương của cả trường trong 1 tháng, 1 năm => báo cáo
    List<Salary> findByMonthAndYear(String month, Integer year);
}
