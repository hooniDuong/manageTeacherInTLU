package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, String> {
    //Lọc theo khoa
    // List<Subject> findByFaculty(String facultyId);

    //Lọc theo bộ môn
    List<Subject> findByDepartment_DepartmentId(String departmentId);

    //Lọc theo giáo viên (Giáo viên nào đang dạy môn này)
    // List<Subject> findByTeacher(String teacherId);

    //Tìm kiếm môn học theo tên/mô tả (không phân biệt hoa thường)
    List<Subject> findByDescriptionContainingIgnoreCase(String description);
}
