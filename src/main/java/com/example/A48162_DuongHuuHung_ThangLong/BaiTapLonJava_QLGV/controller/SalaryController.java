package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.salary.SalaryRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.salary.SalaryService;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/salarys")
public class SalaryController {
    @Autowired private SalaryService service;
    @Autowired private TeacherService teacherService;

    // Endpoint GET "/salarys" hiển thị danh sách bảng lương đã chốt
    @GetMapping
    public String list(
            // Đọc từ khóa tìm kiếm "search" từ form (tìm theo mã lương hoặc tên GV, không bắt buộc)
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        // Kiểm tra xem người dùng có thực hiện tìm kiếm hay không
        if (search != null && !search.trim().isEmpty()) {
            // Nếu có: Gọi service tìm kiếm bảng lương theo mã lương hoặc tên GV
            model.addAttribute("list", service.searchSalaries(search.trim()));
            // Gửi từ khóa tìm kiếm ngược lại giao diện
            model.addAttribute("search", search.trim());
        } else {
            // Nếu không: Tải toàn bộ danh sách bảng lương trong hệ thống
            model.addAttribute("list", service.getAllSalaries());
        }
        // Trả về giao diện "salarys.html"
        return "salarys";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("obj", new SalaryRequestDTO());
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "newSalary";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("obj") SalaryRequestDTO req) {
        service.calculateAndSaveSalary(req);
        return "redirect:/salarys";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.deleteSalary(id);
        return "redirect:/salarys";
    }
}
