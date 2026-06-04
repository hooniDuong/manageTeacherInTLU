package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.studentclass;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.studentclass.StudentClassRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.studentclass.StudentClassResponseDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Faculty;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.StudentClass;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.FacultyRepository;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.StudentClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentClassServiceImpl implements StudentClassService {

    @Autowired
    private StudentClassRepository studentClassRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public List<StudentClassResponseDTO> getAllClasses() {
        // Xử lý lấy toàn bộ dữ liệu lớp học sinh
        List<StudentClass> classes = studentClassRepository.findAll();
        List<StudentClassResponseDTO> responseList = new ArrayList<>();
        for (StudentClass c : classes) {
            responseList.add(mapToDTO(c));
        }
        return responseList;
    }

    @Override
    public StudentClassResponseDTO getClassById(String id) {
        // Tìm lớp theo ID, báo lỗi nếu không thấy
        StudentClass c = studentClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Lớp với ID: " + id));
        return mapToDTO(c);
    }

    @Override
    public List<StudentClassResponseDTO> getClassesByFacultyId(String facultyId) {
        // Lọc lớp theo mã khoa
        List<StudentClass> classes = studentClassRepository.findByFaculty_FacultyId(facultyId);
        List<StudentClassResponseDTO> responseList = new ArrayList<>();
        for (StudentClass c : classes) {
            responseList.add(mapToDTO(c));
        }
        return responseList;
    }

    @Override
    public StudentClassResponseDTO createClass(StudentClassRequestDTO request) {
        // Thêm lớp mới vào hệ thống
        StudentClass c = new StudentClass();
        c.setClassId(request.getClassId());
        c.setName(request.getName());
        c.setMajor(request.getMajor());
        c.setEnrollmentYear(request.getEnrollmentYear());

        // Gắn quan hệ với Khoa
        if (request.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(request.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Lỗi: Không tìm thấy Khoa"));
            c.setFaculty(faculty);
        }

        StudentClass saved = studentClassRepository.save(c);
        return mapToDTO(saved);
    }

    @Override
    public StudentClassResponseDTO updateClass(String id, StudentClassRequestDTO request) {
        // Cập nhật thông tin lớp (không cập nhật ID)
        StudentClass c = studentClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Lớp với ID: " + id));

        c.setName(request.getName());
        c.setMajor(request.getMajor());
        c.setEnrollmentYear(request.getEnrollmentYear());

        if (request.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(request.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Lỗi: Không tìm thấy Khoa"));
            c.setFaculty(faculty);
        }

        StudentClass updated = studentClassRepository.save(c);
        return mapToDTO(updated);
    }

    @Override
    public void deleteClass(String id) {
        // Xóa lớp
        StudentClass c = studentClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Lớp với ID: " + id));
        studentClassRepository.delete(c);
    }

    // Hàm chuyển đổi Entity sang DTO dùng chung để code ngắn gọn
    private StudentClassResponseDTO mapToDTO(StudentClass c) {
        StudentClassResponseDTO dto = new StudentClassResponseDTO();
        dto.setClassId(c.getClassId());
        dto.setName(c.getName());
        dto.setMajor(c.getMajor());
        dto.setEnrollmentYear(c.getEnrollmentYear());
        if (c.getFaculty() != null) {
            dto.setFacultyName(c.getFaculty().getName());
        }
        return dto;
    }

    @Override
    public List<StudentClassResponseDTO> searchClasses(String keyword) {
        // Tìm kiếm các lớp sinh viên theo Tên lớp, Chuyên ngành, hoặc Mã lớp chứa từ khóa
        List<StudentClass> classes = studentClassRepository.findByNameContainingIgnoreCaseOrMajorContainingIgnoreCaseOrClassIdContainingIgnoreCase(keyword, keyword, keyword);
        List<StudentClassResponseDTO> responseList = new ArrayList<>();
        for (StudentClass c : classes) {
            responseList.add(mapToDTO(c));
        }
        return responseList;
    }
}

