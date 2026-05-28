package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //Hển thị danh sách giáo viên
    @GetMapping("/teachers") //truy cập vào localhost:8080/teachers
    public String getAllteachers(Model model) { //dùng để chuyển sang danh HTML
        List<Teacher> teacherList = teacherService.getAllteachers();

        model.addAttribute("teachers", teacherList);

        return "teachers";
    }

    //Thêm giáo viên
    @GetMapping("teachers/addTeacher") //localhost:8080/addTeacher
    public String addTeacherForm(Model model) {
        Teacher teacher = new Teacher();

        model.addAttribute("teacher", teacher);

        return "newTeacher";
    }

    //Lưu giáo viên
    @GetMapping("/teachers/saveTeacher")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.saveTeacher(teacher);

        return "redirect:/teachers";
    }

    //Hiển thị form sửa
//    @GetMapping("/teachers/edit/{id}")
//    public String showEditForm(@PathVariable Long id, Model model) {
//        Teacher teacher = teacherService.getTeacherById(id);
//
//        model.addAttribute("teacher", teacher);
//
//        return "updateTeacher";
//    }

}
