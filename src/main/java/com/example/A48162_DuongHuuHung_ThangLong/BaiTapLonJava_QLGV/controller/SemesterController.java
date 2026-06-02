package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.semester.SemesterRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.semester.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/semesters")
public class SemesterController {
    @Autowired private SemesterService service;

    // Endpoint GET "/semesters" hiển thị danh sách học kỳ
    @GetMapping
    public String list(
            // Lấy tham số tìm kiếm "search" từ form giao diện (không bắt buộc)
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        // Kiểm tra xem người dùng có truyền từ khóa tìm kiếm hay không
        if (search != null && !search.trim().isEmpty()) {
            // Nếu có: Gọi service tìm kiếm các học kỳ theo tên học kỳ
            model.addAttribute("list", service.searchSemestersByName(search.trim()));
            // Gửi từ khóa tìm kiếm ngược lại giao diện để duy trì ô nhập liệu
            model.addAttribute("search", search.trim());
        } else {
            // Nếu không: Lấy danh sách toàn bộ các học kỳ từ DB
            model.addAttribute("list", service.getAllSemesters());
        }
        // Trả về file HTML "semesters.html"
        return "semesters";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("obj", new SemesterRequestDTO());
        return "newSemester";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("obj") SemesterRequestDTO req) {
        com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Semester s = new com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Semester();
        s.setSemesterId(req.getSemesterId());
        s.setName(req.getName());
        s.setYear(req.getYear());
        s.setStartDate(req.getStartDate());
        s.setEndDate(req.getEndDate());
        service.createSemester(s);
        return "redirect:/semesters";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.deleteSemester(id);
        return "redirect:/semesters";
    }
}
