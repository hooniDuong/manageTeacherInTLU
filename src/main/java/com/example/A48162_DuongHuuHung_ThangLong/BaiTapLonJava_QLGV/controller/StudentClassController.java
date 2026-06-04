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

    @GetMapping
    public String list(
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("list", service.searchClasses(search.trim()));
            model.addAttribute("search", search.trim());
        } else {
            model.addAttribute("list", service.getAllClasses());
        }
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
