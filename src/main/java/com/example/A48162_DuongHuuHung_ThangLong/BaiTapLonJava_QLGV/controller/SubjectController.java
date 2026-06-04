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

    @GetMapping
    public String list(
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("list", service.searchSubjectsByDescription(search.trim()));
            model.addAttribute("search", search.trim());
        } else {
            model.addAttribute("list", service.getAllSubjects());
        }
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
