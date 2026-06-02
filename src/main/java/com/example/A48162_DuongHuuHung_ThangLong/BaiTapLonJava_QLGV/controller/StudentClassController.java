package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.studentclass.StudentClassRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.studentclass.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/studentclasss")
public class StudentClassController {
    @Autowired private StudentClassService service;

    // Endpoint GET "/studentclasss" hiển thị danh sách lớp học sinh viên
    @GetMapping
    public String list(
            // Nhận từ khóa tìm kiếm "search" từ thanh địa chỉ hoặc form (không bắt buộc)
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        // Kiểm tra xem người dùng có nhập từ khóa tìm kiếm lớp học hay không
        if (search != null && !search.trim().isEmpty()) {
            // Nếu có: Gọi service tìm kiếm lớp học theo từ khóa (tên lớp, chuyên ngành hoặc mã lớp)
            model.addAttribute("list", service.searchClasses(search.trim()));
            // Gửi từ khóa tìm kiếm ngược lại giao diện để giữ chữ trên ô tìm kiếm
            model.addAttribute("search", search.trim());
        } else {
            // Nếu không: Tải toàn bộ danh sách lớp học sinh từ DB
            model.addAttribute("list", service.getAllClasses());
        }
        // Trả về file HTML "studentclasss.html"
        return "studentclasss";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("obj", new StudentClassRequestDTO());
        return "newStudentClass";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("obj") StudentClassRequestDTO req) {
        service.createClass(req);
        return "redirect:/studentclasss";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.deleteClass(id);
        return "redirect:/studentclasss";
    }
}
