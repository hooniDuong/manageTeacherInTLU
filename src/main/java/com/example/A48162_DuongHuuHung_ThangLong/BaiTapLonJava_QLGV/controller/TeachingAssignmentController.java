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

    @GetMapping
    public String list(
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("list", service.searchAssignments(search.trim()));
            model.addAttribute("search", search.trim());
        } else {
            model.addAttribute("list", service.getAllAssignments());
        }
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
