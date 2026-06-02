package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teachingAssignment.TeachingAssignmentRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.teachingAssignment.TeachingAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachingassignments")
public class TeachingAssignmentController {
    @Autowired private TeachingAssignmentService service;

    // Endpoint GET "/teachingassignments" hiển thị danh sách phân công giảng dạy
    @GetMapping
    public String list(
            // Lấy từ khóa tìm kiếm "search" từ form (tìm theo tên giảng viên, tên môn học hoặc mã phân công, không bắt buộc)
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        // Kiểm tra xem người dùng có thực hiện tìm kiếm hay không
        if (search != null && !search.trim().isEmpty()) {
            // Nếu có: Gọi service tìm kiếm các bản ghi phân công giảng dạy theo từ khóa
            model.addAttribute("list", service.searchAssignments(search.trim()));
            // Gửi từ khóa tìm kiếm ngược lại giao diện để hiển thị trên ô nhập liệu
            model.addAttribute("search", search.trim());
        } else {
            // Nếu không: Tải danh sách tất cả phân công giảng dạy có trong DB
            model.addAttribute("list", service.getAllAssignments());
        }
        // Trả về file HTML "teachingassignments.html"
        return "teachingassignments";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("obj", new TeachingAssignmentRequestDTO());
        return "newTeachingAssignment";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("obj") TeachingAssignmentRequestDTO req) {
        service.createAssignment(req);
        return "redirect:/teachingassignments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.deleteAssignment(id);
        return "redirect:/teachingassignments";
    }
}
