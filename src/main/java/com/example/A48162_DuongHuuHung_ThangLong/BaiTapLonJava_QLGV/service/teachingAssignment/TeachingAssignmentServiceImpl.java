package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.teachingAssignment;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teachingAssignment.TeachingAssignmentRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teachingAssignment.TeachingAssignmentResponseDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.*;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeachingAssignmentServiceImpl implements TeachingAssignmentService {
    @Autowired private TeachingAssignmentRepository repo;
    @Autowired private ClassroomRepository classroomRepo;
    @Autowired private StudentClassRepository studentClassRepo;
    @Autowired private TeacherRepository teacherRepo;
    @Autowired private SubjectRepository subjectRepo;
    @Autowired private SemesterRepository semesterRepo;

    @Override
    public List<TeachingAssignmentResponseDTO> getAllAssignments() {
        // Lấy danh sách phân công giảng dạy
        List<TeachingAssignment> list = repo.findAll();
        List<TeachingAssignmentResponseDTO> res = new ArrayList<>();
        for (TeachingAssignment ta : list) res.add(mapToDTO(ta));
        return res;
    }

    @Override
    public TeachingAssignmentResponseDTO getAssignmentById(String id) {
        // Tìm phân công theo ID
        TeachingAssignment ta = repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy phân công giảng dạy"));
        return mapToDTO(ta);
    }

    @Override
    public List<TeachingAssignmentResponseDTO> getAssignmentsByTeacherAndSemester(String teacherId, String semesterId) {
        // Lọc phân công theo Giáo viên và Học kỳ
        List<TeachingAssignment> list = repo.findByTeacher_TeacherIdAndSemester_SemesterId(teacherId, semesterId);
        List<TeachingAssignmentResponseDTO> res = new ArrayList<>();
        for (TeachingAssignment ta : list) res.add(mapToDTO(ta));
        return res;
    }

    @Override
    public TeachingAssignmentResponseDTO createAssignment(TeachingAssignmentRequestDTO req) {
        // Tạo phân công mới
        TeachingAssignment ta = new TeachingAssignment();
        ta.setAssignmentId(req.getAssignmentId());
        ta.setHours(req.getHours());
        ta.setSchedule(req.getSchedule());
        ta.setStartDate(req.getStartDate());
        ta.setEndDate(req.getEndDate());
        ta.setStatus(req.getStatus());

        if (req.getClassroomId() != null) ta.setClassroom(classroomRepo.findById(req.getClassroomId()).orElse(null));
        if (req.getStudentClassId() != null) ta.setStudentClass(studentClassRepo.findById(req.getStudentClassId()).orElse(null));
        if (req.getTeacherId() != null) ta.setTeacher(teacherRepo.findById(req.getTeacherId()).orElse(null));
        if (req.getSubjectId() != null) ta.setSubject(subjectRepo.findById(req.getSubjectId()).orElse(null));
        if (req.getSemesterId() != null) ta.setSemester(semesterRepo.findById(req.getSemesterId()).orElse(null));

        return mapToDTO(repo.save(ta));
    }

    @Override
    public TeachingAssignmentResponseDTO updateAssignment(String id, TeachingAssignmentRequestDTO req) {
        // Cập nhật phân công
        TeachingAssignment ta = repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy phân công"));
        ta.setHours(req.getHours());
        ta.setSchedule(req.getSchedule());
        ta.setStartDate(req.getStartDate());
        ta.setEndDate(req.getEndDate());
        ta.setStatus(req.getStatus());

        if (req.getClassroomId() != null) ta.setClassroom(classroomRepo.findById(req.getClassroomId()).orElse(null));
        if (req.getStudentClassId() != null) ta.setStudentClass(studentClassRepo.findById(req.getStudentClassId()).orElse(null));
        if (req.getTeacherId() != null) ta.setTeacher(teacherRepo.findById(req.getTeacherId()).orElse(null));
        if (req.getSubjectId() != null) ta.setSubject(subjectRepo.findById(req.getSubjectId()).orElse(null));
        if (req.getSemesterId() != null) ta.setSemester(semesterRepo.findById(req.getSemesterId()).orElse(null));

        return mapToDTO(repo.save(ta));
    }

    @Override
    public void deleteAssignment(String id) {
        // Xóa phân công
        repo.deleteById(id);
    }

    // Hàm chuyển đổi Entity sang DTO
    private TeachingAssignmentResponseDTO mapToDTO(TeachingAssignment ta) {
        TeachingAssignmentResponseDTO dto = new TeachingAssignmentResponseDTO();
        dto.setAssignmentId(ta.getAssignmentId());
        dto.setHours(ta.getHours());
        dto.setSchedule(ta.getSchedule());
        dto.setStatus(ta.getStatus());
        if(ta.getClassroom() != null) dto.setClassroomName(ta.getClassroom().getName());
        if(ta.getStudentClass() != null) dto.setStudentClassName(ta.getStudentClass().getName());
        if(ta.getTeacher() != null) dto.setTeacherName(ta.getTeacher().getName());
        if(ta.getSubject() != null) dto.setSubjectName(ta.getSubject().getDescription());
        if(ta.getSemester() != null) dto.setSemesterName(ta.getSemester().getName());
        return dto;
    }

    @Override
    public List<TeachingAssignmentResponseDTO> searchAssignments(String keyword) {
        // Tìm kiếm phân công theo tên giảng viên, tên/mô tả môn học, hoặc mã phân công chứa từ khóa
        List<TeachingAssignment> list = repo.findByTeacher_NameContainingIgnoreCaseOrSubject_DescriptionContainingIgnoreCaseOrAssignmentIdContainingIgnoreCase(keyword, keyword, keyword);
        List<TeachingAssignmentResponseDTO> res = new ArrayList<>();
        for (TeachingAssignment ta : list) {
            res.add(mapToDTO(ta));
        }
        return res;
    }
}

