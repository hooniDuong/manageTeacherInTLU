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

    // Endpoint GET "/facultys" để hiển thị danh sách tất cả các khoa
    @GetMapping
    public String list(
            // Lấy từ khóa tìm kiếm "search" từ thanh địa chỉ hoặc form (không bắt buộc)
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        // Kiểm tra xem từ khóa tìm kiếm có giá trị hợp lệ hay không
        if (search != null && !search.trim().isEmpty()) {
            // Nếu có: Gọi service tìm kiếm khoa theo tên chứa từ khóa
            model.addAttribute("list", service.searchFacultiesByName(search.trim()));
            // Gửi từ khóa tìm kiếm ngược lại view để lưu vết trên ô input
            model.addAttribute("search", search.trim());
        } else {
            // Nếu không: Tải toàn bộ danh sách các khoa từ DB
            model.addAttribute("list", service.getAllFaculties());
        }
        // Trả về giao diện HTML "facultys.html"
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
