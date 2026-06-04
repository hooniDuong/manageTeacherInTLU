package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.semester;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Semester;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterServiceImpl implements SemesterService {
    @Autowired private SemesterRepository repo;

    @Override
    public List<Semester> getAllSemesters() {
        return repo.findAll();
    }

    @Override
    public Semester getSemesterById(String id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy Học kỳ"));
    }

    @Override
    public Semester createSemester(Semester semester) {
        return repo.save(semester);
    }

    @Override
    public Semester updateSemester(String id, Semester semester) {
        Semester s = repo.findById(id).orElseThrow(() -> new RuntimeException("Lỗi"));
        s.setName(semester.getName());
        s.setStartDate(semester.getStartDate());
        s.setEndDate(semester.getEndDate());
        return repo.save(s);
    }

    @Override
    public void deleteSemester(String id) {
        repo.deleteById(id);
    }

    @Override
    public List<Semester> searchSemestersByName(String name) {
        // Tìm kiếm các học kỳ theo tên chứa từ khóa
        return repo.findByNameContainingIgnoreCase(name);
    }
}
