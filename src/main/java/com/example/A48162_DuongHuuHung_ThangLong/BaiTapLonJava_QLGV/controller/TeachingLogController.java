package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.teachinglog.TeachingLogRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.teachingLog.TeachingLogService;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.TeachingAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachinglogs")
public class TeachingLogController {
    @Autowired private TeachingLogService service;
    @Autowired private TeachingAssignmentRepository assignmentRepo;

    // Endpoint GET "/teachinglogs" hiển thị danh sách nhật ký giảng dạy
    @GetMapping
    public String list(
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("list", service.searchLogs(search.trim()));
            model.addAttribute("search", search.trim());
        } else {
            model.addAttribute("list", service.getAllLogs());
        }
        return "teachinglogs";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("obj", new TeachingLogRequestDTO());
        return "newTeachingLog";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("obj") TeachingLogRequestDTO req) {
        com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.TeachingLog log = new com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.TeachingLog();
        log.setLogId(req.getLogId());
        log.setTeachingDate(req.getTeachingDate());
        log.setHoursTaught(req.getActualHours() != null ? (double) req.getActualHours() : null); // DTO calls it actualHours
        log.setTopic(req.getContent());           // DTO calls it content
        log.setStatus(req.getNote());             // DTO calls it note
        
        if (req.getAssignmentId() != null) {
            log.setAssignment(assignmentRepo.findById(req.getAssignmentId()).orElse(null));
        }
        
        service.createLog(log);
        return "redirect:/teachinglogs";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.deleteLog(id);
        return "redirect:/teachinglogs";
    }
}
