package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.contract.ContractRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.contract.ContractService;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contracts")
public class ContractController {
    @Autowired private ContractService service;
    @Autowired private TeacherRepository teacherRepo;

    // Endpoint GET "/contracts" để hiển thị danh sách hợp đồng
    @GetMapping
    public String list(
            // Nhận từ khóa tìm kiếm "search" từ form giao diện gửi lên (không bắt buộc)
            @RequestParam(value = "search", required = false) String search, 
            Model model
    ) {
        // Kiểm tra xem người dùng có thực hiện tìm kiếm hay không
        if (search != null && !search.trim().isEmpty()) {
            // Nếu có: Gọi service tìm kiếm hợp đồng (theo loại hợp đồng, mã hợp đồng hoặc tên giảng viên)
            model.addAttribute("list", service.searchContracts(search.trim()));
            // Gửi từ khóa tìm kiếm ngược lại ra ngoài giao diện để điền sẵn vào ô tìm kiếm
            model.addAttribute("search", search.trim());
        } else {
            // Nếu không tìm kiếm: Gọi service lấy toàn bộ hợp đồng hiện có trong cơ sở dữ liệu
            model.addAttribute("list", service.getAllContracts());
        }
        // Trả về giao diện "contracts.html"
        return "contracts";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("obj", new ContractRequestDTO());
        return "newContract";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("obj") ContractRequestDTO req) {
        com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Contract c = new com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Contract();
        c.setContractId(req.getContractId());
        c.setContractType(req.getContractType());
        c.setStartDate(req.getStartDate());
        c.setEndDate(req.getEndDate());
        c.setBaseSalary(req.getBaseSalary());
        c.setStatus(req.getStatus());
        
        if (req.getTeacherId() != null) {
            c.setTeacher(teacherRepo.findById(req.getTeacherId()).orElse(null));
        }
        
        service.createContract(c);
        return "redirect:/contracts";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.deleteContract(id);
        return "redirect:/contracts";
    }
}
