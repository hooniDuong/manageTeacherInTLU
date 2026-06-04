package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.user.UserRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired private UserService service;

    @GetMapping
    public String list(
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("list", service.searchUsers(search.trim()));
            model.addAttribute("search", search.trim());
        } else {
            model.addAttribute("list", service.getAllUsers());
        }
        return "users";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("obj", new UserRequestDTO());
        return "newUser";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("obj") UserRequestDTO req) {
        service.createUser(req);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.deleteUser(id);
        return "redirect:/users";
    }
}
