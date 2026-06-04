package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.department.DepartmentRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String list(
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("list", departmentService.searchDepartmentsByName(search.trim()));
            model.addAttribute("search", search.trim());
        } else {
            model.addAttribute("list", departmentService.getAllDepartments());
        }
        return "departments";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("obj", new DepartmentRequestDTO());
        return "newDepartment";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("obj") DepartmentRequestDTO request) {
        departmentService.addDepartment(request);
        return "redirect:/departments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}
