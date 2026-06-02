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

    // Endpoint GET "/users" hiển thị danh sách tài khoản người dùng
    @GetMapping
    public String list(
            // Lấy từ khóa tìm kiếm "search" từ form giao diện gửi lên (tìm theo tên tài khoản hoặc email, không bắt buộc)
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        // Kiểm tra xem người dùng có nhập từ khóa tìm kiếm tài khoản hay không
        if (search != null && !search.trim().isEmpty()) {
            // Nếu có: Gọi service thực hiện tìm kiếm tài khoản theo username hoặc email chứa từ khóa
            model.addAttribute("list", service.searchUsers(search.trim()));
            // Gửi từ khóa tìm kiếm ngược lại giao diện để lưu vết hiển thị
            model.addAttribute("search", search.trim());
        } else {
            // Nếu không: Lấy toàn bộ danh sách tài khoản người dùng từ hệ thống
            model.addAttribute("list", service.getAllUsers());
        }
        // Trả về file HTML "users.html"
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
