package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.subject;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.subject.SubjectRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.subject.SubjectResponseDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Subject;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.DepartmentRepository;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired private SubjectRepository repo;
    @Autowired private DepartmentRepository deptRepo;

    @Override
    public List<SubjectResponseDTO> getAllSubjects() {
        // Lấy danh sách môn học
        List<Subject> list = repo.findAll();
        List<SubjectResponseDTO> res = new ArrayList<>();
        for (Subject s : list) res.add(mapToDTO(s));
        return res;
    }

    @Override
    public SubjectResponseDTO getSubjectById(String id) {
        // Lấy chi tiết môn học
        return mapToDTO(repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy môn học")));
    }

    @Override
    public List<SubjectResponseDTO> getSubjectsByDepartment(String departmentId) {
        // Lọc môn học theo Bộ môn quản lý
        List<Subject> list = repo.findByDepartment_DepartmentId(departmentId);
        List<SubjectResponseDTO> res = new ArrayList<>();
        for (Subject s : list) res.add(mapToDTO(s));
        return res;
    }

    @Override
    public SubjectResponseDTO createSubject(SubjectRequestDTO req) {
        // Tạo môn học mới
        Subject s = new Subject();
        s.setSubjectId(req.getSubjectId());
        s.setCredits(req.getCredits());
        s.setDescription(req.getDescription());
        if (req.getDepartmentId() != null) {
            s.setDepartment(deptRepo.findById(req.getDepartmentId()).orElse(null));
        }
        return mapToDTO(repo.save(s));
    }

    @Override
    public SubjectResponseDTO updateSubject(String id, SubjectRequestDTO req) {
        // Sửa môn học
        Subject s = repo.findById(id).orElseThrow(() -> new RuntimeException("Lỗi"));
        s.setCredits(req.getCredits());
        s.setDescription(req.getDescription());
        if (req.getDepartmentId() != null) {
            s.setDepartment(deptRepo.findById(req.getDepartmentId()).orElse(null));
        }
        return mapToDTO(repo.save(s));
    }

    @Override
    public void deleteSubject(String id) {
        // Xóa môn học
        repo.deleteById(id);
    }

    @Override
    public List<SubjectResponseDTO> searchSubjectsByDescription(String description) {
        List<Subject> list = repo.findByDescriptionContainingIgnoreCase(description);
        List<SubjectResponseDTO> res = new ArrayList<>();
        for (Subject s : list) res.add(mapToDTO(s));
        return res;
    }

    // Chuyển Entity sang DTO
    private SubjectResponseDTO mapToDTO(Subject s) {
        SubjectResponseDTO dto = new SubjectResponseDTO();
        dto.setSubjectId(s.getSubjectId());
        dto.setCredits(s.getCredits());
        dto.setDescription(s.getDescription());
        if(s.getDepartment() != null) dto.setDepartmentName(s.getDepartment().getName());
        return dto;
    }
}

