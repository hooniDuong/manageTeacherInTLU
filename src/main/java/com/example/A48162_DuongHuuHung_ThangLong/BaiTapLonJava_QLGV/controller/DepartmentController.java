package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.department.DepartmentRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Endpoint GET "/departments" để tải danh sách các bộ môn
    @GetMapping
    public String list(
            // Đọc từ khóa tìm kiếm "search" được truyền từ URL hoặc form gửi lên (không bắt buộc)
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        // Kiểm tra xem từ khóa tìm kiếm có dữ liệu và không trống rỗng
        if (search != null && !search.trim().isEmpty()) {
            // Nếu có tìm kiếm: Thực hiện gọi hàm tìm kiếm bộ môn theo tên của Service và lưu vào "list"
            model.addAttribute("list", departmentService.searchDepartmentsByName(search.trim()));
            // Gửi từ khóa vừa tìm ngược lại UI để giữ nguyên chữ hiển thị ở ô tìm kiếm
            model.addAttribute("search", search.trim());
        } else {
            // Nếu không tìm kiếm: Gọi hàm lấy toàn bộ danh sách bộ môn hiện có
            model.addAttribute("list", departmentService.getAllDepartments());
        }
        // Trả về giao diện "departments.html"
        return "departments";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("obj", new DepartmentRequestDTO());
        return "newDepartment";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("obj") DepartmentRequestDTO request) {
        departmentService.addDepartment(request);
        return "redirect:/departments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}
