package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.department;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.department.DepartmentRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.department.DepartmentResponseDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Department;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Faculty;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.DepartmentRepository;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public DepartmentResponseDTO addDepartment(DepartmentRequestDTO requestDTO) {
        // Convert DTO -> Entity
        Department department = new Department();
        department.setDepartmentId(requestDTO.getDepartmentId());
        department.setName(requestDTO.getName());
        department.setDescription(requestDTO.getDescription());
        department.setLocationOffice(requestDTO.getLocationOffice());

        // Lấy Entity Khoa (Faculty)
        if (requestDTO.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(requestDTO.getFacultyId()).orElse(null);
            department.setFaculty(faculty);
        }

        // Lưu Entity vào Database qua Repository
        Department savedDepartment = departmentRepository.save(department);

        // Convert Entity vừa lưu -> ResponseDTO để trả về
        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();
        responseDTO.setDepartmentId(savedDepartment.getDepartmentId());
        responseDTO.setName(savedDepartment.getName());
        responseDTO.setLocationOffice(savedDepartment.getLocationOffice());
        responseDTO.setDescription(savedDepartment.getDescription());
        
        if (savedDepartment.getFaculty() != null) {
            // Chỉ lấy tên Khoa bày ra đĩa
            responseDTO.setFacultyName(savedDepartment.getFaculty().getName());
        }

        return responseDTO;
    }

    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {
        // Entity
        List<Department> departments = departmentRepository.findAll();
        
        // ResponseDTO
        List<DepartmentResponseDTO> responseList = new ArrayList<>();

        for (Department dept : departments) {
            DepartmentResponseDTO dto = new DepartmentResponseDTO();
            dto.setDepartmentId(dept.getDepartmentId());
            dto.setName(dept.getName());
            dto.setLocationOffice(dept.getLocationOffice());
            dto.setDescription(dept.getDescription());
            if (dept.getFaculty() != null) {
                dto.setFacultyName(dept.getFaculty().getName());
            }
            responseList.add(dto);
        }
        return responseList;
    }

    @Override
    public void deleteDepartment(String id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<DepartmentResponseDTO> searchDepartmentsByName(String name) {
        List<Department> departments = departmentRepository.findByNameContainingIgnoreCase(name);
        List<DepartmentResponseDTO> responseList = new ArrayList<>();
        for (Department dept : departments) {
            DepartmentResponseDTO dto = new DepartmentResponseDTO();
            dto.setDepartmentId(dept.getDepartmentId());
            dto.setName(dept.getName());
            dto.setLocationOffice(dept.getLocationOffice());
            dto.setDescription(dept.getDescription());
            if (dept.getFaculty() != null) {
                dto.setFacultyName(dept.getFaculty().getName());
            }
            responseList.add(dto);
        }
        return responseList;
    }
}
