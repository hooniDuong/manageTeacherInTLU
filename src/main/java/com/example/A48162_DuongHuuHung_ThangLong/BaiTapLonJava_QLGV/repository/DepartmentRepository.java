package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Department;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    //Cần lọc theo khoa
    List<Department> findByFaculty_FacultyId(String facultyId);
    //Trả về một list danh sách deparment lọc theo khoa(Faculty)

    //Tìm kiếm bộ môn theo tên (không phân biệt hoa thường)
    List<Department> findByNameContainingIgnoreCase(String name);
}