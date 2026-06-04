package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.RoleRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired private RoleService service;

    @GetMapping
    public String list(
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("list", service.searchRolesByName(search.trim()));
            model.addAttribute("search", search.trim());
        } else {
            model.addAttribute("list", service.getAllRoles());
        }
        return "roles";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("obj", new RoleRequestDTO());
        return "newRole";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("obj") RoleRequestDTO req) {
        service.createRole(req);
        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.deleteRole(id);
        return "redirect:/roles";
    }
}
