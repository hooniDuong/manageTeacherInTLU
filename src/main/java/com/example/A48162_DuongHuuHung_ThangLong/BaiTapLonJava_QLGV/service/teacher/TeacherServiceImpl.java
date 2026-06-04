package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.teacher;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teacher.TeacherRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teacher.TeacherResponseDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Department;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Teacher;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.User;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.DepartmentRepository;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.TeacherRepository;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<TeacherResponseDTO> getAllTeachers() {
        // Lấy danh sách toàn bộ giáo viên
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherResponseDTO> responseList = new ArrayList<>();
        for (Teacher t : teachers) {
            responseList.add(mapToDTO(t));
        }
        return responseList;
    }

    @Override
    public TeacherResponseDTO getTeacherById(String id) {
        // Xem hồ sơ giáo viên theo ID
        Teacher t = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Giáo viên với ID: " + id));
        return mapToDTO(t);
    }

    @Override
    public List<TeacherResponseDTO> getTeachersByDepartment(String departmentId) {
        // Lọc giáo viên theo bộ môn
        List<Teacher> teachers = teacherRepository.findByDepartment_DepartmentId(departmentId);
        List<TeacherResponseDTO> responseList = new ArrayList<>();
        for (Teacher t : teachers) {
            responseList.add(mapToDTO(t));
        }
        return responseList;
    }

    @Override
    public TeacherResponseDTO createTeacher(TeacherRequestDTO request) {
        // Khởi tạo giáo viên mới
        Teacher t = new Teacher();
        t.setTeacherId(request.getTeacherId());
        t.setName(request.getName());
        t.setGender(request.getGender());
        t.setBirthday(request.getBirthday());
        t.setPhone(request.getPhone());
        t.setEmail(request.getEmail());
        t.setAddress(request.getAddress());
        t.setDegree(request.getDegree());
        t.setPosition(request.getPosition());
        t.setHireDate(request.getHireDate());
        t.setSalary(request.getSalary());
        t.setStatus(request.getStatus());
        t.setAvatar(request.getAvatar());

        if (request.getDepartmentId() != null) {
            Department d = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Bộ môn"));
            t.setDepartment(d);
        }

        if (request.getUserId() != null) {
            User u = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Tài khoản"));
            t.setUser(u);
        }

        Teacher saved = teacherRepository.save(t);
        return mapToDTO(saved);
    }

    @Override
    public TeacherResponseDTO updateTeacher(String id, TeacherRequestDTO request) {
        // Cập nhật thông tin giáo viên
        Teacher t = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Giáo viên với ID: " + id));
        
        t.setName(request.getName());
        t.setGender(request.getGender());
        t.setBirthday(request.getBirthday());
        t.setPhone(request.getPhone());
        t.setEmail(request.getEmail());
        t.setAddress(request.getAddress());
        t.setDegree(request.getDegree());
        t.setPosition(request.getPosition());
        t.setHireDate(request.getHireDate());
        t.setSalary(request.getSalary());
        t.setStatus(request.getStatus());
        t.setAvatar(request.getAvatar());

        if (request.getDepartmentId() != null) {
            Department d = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Bộ môn"));
            t.setDepartment(d);
        }

        if (request.getUserId() != null) {
            User u = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Tài khoản"));
            t.setUser(u);
        }

        Teacher updated = teacherRepository.save(t);
        return mapToDTO(updated);
    }

    @Override
    public void deleteTeacher(String id) {
        // Xóa giáo viên
        Teacher t = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Giáo viên với ID: " + id));
        teacherRepository.delete(t);
    }

    // Hàm chuyển đổi Entity sang DTO
    private TeacherResponseDTO mapToDTO(Teacher t) {
        TeacherResponseDTO dto = new TeacherResponseDTO();
        dto.setTeacherId(t.getTeacherId());
        dto.setName(t.getName());
        dto.setGender(t.getGender());
        dto.setBirthday(t.getBirthday());
        dto.setPhone(t.getPhone());
        dto.setEmail(t.getEmail());
        dto.setAddress(t.getAddress());
        dto.setDegree(t.getDegree());
        dto.setPosition(t.getPosition());
        dto.setHireDate(t.getHireDate());
        dto.setSalary(t.getSalary());
        dto.setStatus(t.getStatus());
        dto.setAvatar(t.getAvatar());
        if (t.getDepartment() != null) {
            dto.setDepartmentName(t.getDepartment().getName());
            dto.setDepartmentId(t.getDepartment().getDepartmentId());
        }
        if (t.getUser() != null) {
            dto.setUserId(t.getUser().getUserId());
        }
        return dto;
    }

    @Override
    public List<TeacherResponseDTO> searchTeachersByName(String name) {
        List<Teacher> teachers = teacherRepository.findByNameContainingIgnoreCase(name);
        List<TeacherResponseDTO> responseList = new ArrayList<>();
        for (Teacher t : teachers) {
            responseList.add(mapToDTO(t));
        }
        return responseList;
    }
}

