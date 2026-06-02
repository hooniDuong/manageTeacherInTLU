package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.classroom.ClassroomRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.classroom.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/classrooms")
public class ClassroomController {
    @Autowired private ClassroomService service;

    // Endpoint GET "/classrooms" để hiển thị danh sách phòng học
    @GetMapping
    public String list(
            // Nhận tham số tìm kiếm "search" gửi từ ô nhập liệu trên giao diện (không bắt buộc)
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        // Kiểm tra xem người dùng có nhập từ khóa tìm kiếm hay không
        if (search != null && !search.trim().isEmpty()) {
            // Nếu có: Gọi service tìm kiếm phòng học theo tên và gán kết quả vào thuộc tính "list"
            model.addAttribute("list", service.searchClassroomsByName(search.trim()));
            // Đẩy lại từ khóa tìm kiếm ra giao diện để điền sẵn vào ô nhập liệu
            model.addAttribute("search", search.trim());
        } else {
            // Nếu không tìm kiếm: Gọi service lấy toàn bộ danh sách phòng học gán vào "list"
            model.addAttribute("list", service.getAllClassrooms());
        }
        // Trả về tên file template HTML "classrooms.html"
        return "classrooms";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("obj", new ClassroomRequestDTO());
        return "newClassroom";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("obj") ClassroomRequestDTO req) {
        com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Classroom c = new com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Classroom();
        c.setClassroomId(req.getClassroomId());
        c.setName(req.getName());
        c.setCapacity(req.getCapacity());
        c.setBuilding(req.getLocation());
        service.createClassroom(c);
        return "redirect:/classrooms";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.deleteClassroom(id);
        return "redirect:/classrooms";
    }
}
