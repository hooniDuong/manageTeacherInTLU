package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.subject.SubjectRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired private SubjectService service;

    // Endpoint GET "/subjects" hiển thị danh sách môn học
    @GetMapping
    public String list(
            // Lấy từ khóa tìm kiếm "search" từ form (tìm theo tên/mô tả môn học, không bắt buộc)
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        // Kiểm tra xem người dùng có thực hiện nhập từ khóa tìm kiếm hay không
        if (search != null && !search.trim().isEmpty()) {
            // Nếu có: Gọi service tìm kiếm môn học theo tên/mô tả
            model.addAttribute("list", service.searchSubjectsByDescription(search.trim()));
            // Trả ngược từ khóa tìm kiếm ra giao diện
            model.addAttribute("search", search.trim());
        } else {
            // Nếu không: Lấy toàn bộ danh sách môn học hiện có
            model.addAttribute("list", service.getAllSubjects());
        }
        // Trả về file HTML "subjects.html"
        return "subjects";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("obj", new SubjectRequestDTO());
        return "newSubject";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("obj") SubjectRequestDTO req) {
        service.createSubject(req);
        return "redirect:/subjects";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.deleteSubject(id);
        return "redirect:/subjects";
    }
}
