package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.faculty.FacultyRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.faculty.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/facultys")
public class FacultyController {
    @Autowired private FacultyService service;

    @GetMapping
    public String list(
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("list", service.searchFacultiesByName(search.trim()));
            model.addAttribute("search", search.trim());
        } else {
            model.addAttribute("list", service.getAllFaculties());
        }
        return "facultys";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("obj", new FacultyRequestDTO());
        return "newFaculty";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("obj") FacultyRequestDTO req) {
        service.createFaculty(req);
        return "redirect:/facultys";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.deleteFaculty(id);
        return "redirect:/facultys";
    }
}
