package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.role;



import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.RoleRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.RoleResponseDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Role;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleResponseDTO> getAllRoles() {
        // Lấy danh sách các Quyền (Roles) trong hệ thống
        List<Role> roles = roleRepository.findAll();
        List<RoleResponseDTO> responseList = new ArrayList<>();
        for (Role r : roles) {
            RoleResponseDTO dto = new RoleResponseDTO();
            dto.setRoleId(r.getRoleId());
            dto.setRoleName(r.getRoleName());
            responseList.add(dto);
        }
        return responseList;
    }

    @Override
    public RoleResponseDTO getRoleById(String id) {
        // Tìm quyền theo ID
        Role r = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Quyền với ID: " + id));
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setRoleId(r.getRoleId());
        dto.setRoleName(r.getRoleName());
        return dto;
    }

    @Override
    public RoleResponseDTO createRole(RoleRequestDTO request) {
        // Khởi tạo quyền mới
        Role r = new Role();
        r.setRoleId(request.getRoleId());
        r.setRoleName(request.getRoleName());
        Role saved = roleRepository.save(r);
        
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setRoleId(saved.getRoleId());
        dto.setRoleName(saved.getRoleName());
        return dto;
    }

    @Override
    public void deleteRole(String id) {
        // Xóa quyền
        Role r = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Quyền với ID: " + id));
        roleRepository.delete(r);
    }

    @Override
    public List<RoleResponseDTO> searchRolesByName(String name) {
        // Tìm kiếm các Quyền theo tên chứa từ khóa
        List<Role> roles = roleRepository.findByRoleNameContainingIgnoreCase(name);
        List<RoleResponseDTO> responseList = new ArrayList<>();
        for (Role r : roles) {
            RoleResponseDTO dto = new RoleResponseDTO();
            dto.setRoleId(r.getRoleId());
            dto.setRoleName(r.getRoleName());
            responseList.add(dto);
        }
        return responseList;
    }
}

