package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentClassRepository extends JpaRepository<StudentClass, String> {
    //Tìm các lớp sinh viên thuộc về một khoa
    List<StudentClass> findByFaculty(String facultyId);

    //Tìm các sinh viên theo năm nhập học (lọc theo enrollmentYear)
    List<StudentClass> findByEnrollmentYear(Integer enrollmentYear);
}
