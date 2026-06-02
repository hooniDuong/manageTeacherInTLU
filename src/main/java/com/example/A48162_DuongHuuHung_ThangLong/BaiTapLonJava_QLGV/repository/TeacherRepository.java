package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
    //Lấy giáo viên dựa vào ID của tài khoản users (Khi user đăng nhập ta biết đó là giáo viên nào)
    Teacher findByUser_Username(String username);

    //Lọc giáo viên theo danh sách khoa
    List<Teacher> findByDepartment_Faculty_FacultyId(String facultyId);

    //Lọc giáo viên theo danh sách bộ môn
    List<Teacher> findByDepartment_DepartmentId(String departmentId);

    //Lọc giáo viên theo trạng thái (còn làm việc hay là không?)
    List<Teacher> findByStatus(String status);

    //Tìm kiếm giáo viên theo tên (không phân biệt hoa thường)
    List<Teacher> findByNameContainingIgnoreCase(String name);
}
