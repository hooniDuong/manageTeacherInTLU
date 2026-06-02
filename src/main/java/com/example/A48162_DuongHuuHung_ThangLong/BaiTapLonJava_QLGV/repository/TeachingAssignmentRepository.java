package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.TeachingAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeachingAssignmentRepository extends JpaRepository<TeachingAssignment, String> {
    //Lấy toàn bộ lịch giảng dạy của giáo viên trong một học kì cụ thể
    List<TeachingAssignment> findByTeacher_TeacherIdAndSemester_SemesterId(String teacherId, String semesterId);

    //Lấy thời khoá biểu của một lớp sinh viên trong 1 học kì (Lớp đó đang có giờ giấc như thể nào để tránh hoặc xếp lớp)
    List<TeachingAssignment> findByStudentClass_ClassIdAndSemester_SemesterId(String classId, String semesterId);

    // Tìm kiếm phân công giảng dạy theo tên giáo viên, tên/mô tả môn học, hoặc mã phân công
    List<TeachingAssignment> findByTeacher_NameContainingIgnoreCaseOrSubject_DescriptionContainingIgnoreCaseOrAssignmentIdContainingIgnoreCase(String teacherName, String subjectDesc, String assignmentId);
}
