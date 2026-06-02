package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.RoleRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired private RoleService service;

    // Endpoint GET "/roles" hiển thị danh sách vai trò
    @GetMapping
    public String list(
            // Lấy từ khóa "search" người dùng nhập từ ô tìm kiếm (không bắt buộc)
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        // Kiểm tra xem người dùng có truyền từ khóa tìm kiếm lên hay không
        if (search != null && !search.trim().isEmpty()) {
            // Nếu có: Gọi service tìm kiếm quyền theo tên vai trò
            model.addAttribute("list", service.searchRolesByName(search.trim()));
            // Trả từ khóa ngược ra giao diện để tiếp tục hiển thị trên ô nhập liệu
            model.addAttribute("search", search.trim());
        } else {
            // Nếu không: Lấy toàn bộ danh sách vai trò trong hệ thống
            model.addAttribute("list", service.getAllRoles());
        }
        // Trả về giao diện HTML "roles.html"
        return "roles";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("obj", new RoleRequestDTO());
        return "newRole";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("obj") RoleRequestDTO req) {
        service.createRole(req);
        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.deleteRole(id);
        return "redirect:/roles";
    }
}
