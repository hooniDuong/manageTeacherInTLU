package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teacher.TeacherRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public String listTeachers(
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("teachers", teacherService.searchTeachersByName(search.trim()));
            model.addAttribute("search", search.trim());
        } else {
            model.addAttribute("teachers", teacherService.getAllTeachers());
        }
        return "teachers";
    }

    @GetMapping("/new")
    public String createTeacherForm(Model model) {
        model.addAttribute("teacher", new TeacherRequestDTO());
        return "newTeacher";
    }

    @PostMapping("/save")
    public String saveTeacher(@ModelAttribute("teacher") TeacherRequestDTO request) {
        teacherService.createTeacher(request);
        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String editTeacherForm(@PathVariable String id, Model model) {
        // Ta dùng DTO để fill dữ liệu lên form, nhưng do hàm get trả về ResponseDTO
        // Thực tế form cần RequestDTO, nên ta map tạm các trường cần thiết
        model.addAttribute("teacher", teacherService.getTeacherById(id));
        model.addAttribute("id", id);
        return "updateTeacher";
    }

    @PostMapping("/update/{id}")
    public String updateTeacher(@PathVariable String id, @ModelAttribute("teacher") TeacherRequestDTO request) {
        teacherService.updateTeacher(id, request);
        return "redirect:/teachers";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable String id) {
        teacherService.deleteTeacher(id);
        return "redirect:/teachers";
    }
}
