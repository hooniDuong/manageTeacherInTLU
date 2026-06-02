package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentClassRepository extends JpaRepository<StudentClass, String> {
    //Tìm các lớp sinh viên thuộc về một khoa
    List<StudentClass> findByFaculty_FacultyId(String facultyId);

    //Tìm các sinh viên theo năm nhập học (lọc theo enrollmentYear)
    List<StudentClass> findByEnrollmentYear(Integer enrollmentYear);

    // Tìm kiếm lớp sinh viên theo Tên lớp, Chuyên ngành, hoặc Mã lớp (không phân biệt chữ hoa, chữ thường)
    List<StudentClass> findByNameContainingIgnoreCaseOrMajorContainingIgnoreCaseOrClassIdContainingIgnoreCase(String name, String major, String classId);
}
