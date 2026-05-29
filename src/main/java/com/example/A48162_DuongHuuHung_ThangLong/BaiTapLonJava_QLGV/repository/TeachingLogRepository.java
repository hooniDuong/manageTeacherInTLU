package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.TeachingLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeachingLogRepository extends JpaRepository<TeachingLog, String> {
    // Lấy danh sách các buổi dạy của một lượt Phân công giảng dạy
    List<TeachingLog> findByAssignment_AssignmentId(String assignmentId);
}
