package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.teachingLog;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.TeachingLog;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.TeachingLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachingLogServiceImpl implements TeachingLogService {
    @Autowired private TeachingLogRepository repo;

    @Override
    public List<TeachingLog> getLogsByAssignmentId(String assignmentId) {
        return repo.findByAssignment_AssignmentId(assignmentId);
    }

    @Override
    public List<TeachingLog> getAllLogs() {
        return repo.findAll();
    }

    @Override
    public TeachingLog getLogById(String id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Lỗi không tìm thấy"));
    }

    @Override
    public TeachingLog createLog(TeachingLog log) {
        return repo.save(log);
    }

    @Override
    public TeachingLog updateLog(String id, TeachingLog log) {
        TeachingLog l = repo.findById(id).orElseThrow(() -> new RuntimeException("Lỗi"));
        l.setTeachingDate(log.getTeachingDate());
        l.setHoursTaught(log.getHoursTaught());
        l.setTopic(log.getTopic());
        l.setStatus(log.getStatus());
        return repo.save(l);
    }

    @Override
    public void deleteLog(String id) {
        repo.deleteById(id);
    }

    @Override
    public List<TeachingLog> searchLogs(String keyword) {
        // Tìm kiếm các buổi dạy theo nội dung/chủ đề bài giảng, tên giảng viên, hoặc mã nhật ký chứa từ khóa
        return repo.findByTopicContainingIgnoreCaseOrAssignment_Teacher_NameContainingIgnoreCaseOrLogIdContainingIgnoreCase(keyword, keyword, keyword);
    }
}
