package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Department;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    //Cần lọc theo khoa
    List<Department> findByFaculty(String facultyId);
    //Trả về một list danh sách deparment lọc theo khoa(Faculty)
}
