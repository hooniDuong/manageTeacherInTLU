package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.salary;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.salary.SalaryRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.salary.SalaryResponseDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Salary;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Teacher;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.SalaryRepository;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired private SalaryRepository salaryRepo;
    @Autowired private TeacherRepository teacherRepo;

    @Override
    public List<SalaryResponseDTO> getAllSalaries() {
        // Lấy danh sách bảng lương
        List<Salary> list = salaryRepo.findAll();
        List<SalaryResponseDTO> res = new ArrayList<>();
        for (Salary s : list) res.add(mapToDTO(s));
        return res;
    }

    @Override
    public List<SalaryResponseDTO> getSalariesByMonthAndYear(Integer month, Integer year) {
        // Lọc lương theo tháng năm (Dùng Java Stream/For-loop tạm thời)
        List<Salary> list = salaryRepo.findAll(); 
        List<SalaryResponseDTO> res = new ArrayList<>();
        for (Salary s : list) {
            if (s.getMonth().equals(month) && s.getYear().equals(year)) {
                res.add(mapToDTO(s));
            }
        }
        return res;
    }

    @Override
    public SalaryResponseDTO calculateAndSaveSalary(SalaryRequestDTO req) {
        // Khởi tạo bảng lương cho 1 giáo viên
        Salary s = new Salary();
        s.setSalaryId(req.getSalaryId());
        s.setMonth(req.getMonth());
        s.setYear(req.getYear());
        s.setTotalHours(req.getTotalHours());
        
        java.math.BigDecimal bonus = req.getBonus() != null ? req.getBonus() : java.math.BigDecimal.ZERO;
        java.math.BigDecimal deduction = req.getDeduction() != null ? req.getDeduction() : java.math.BigDecimal.ZERO;
        s.setBonus(bonus);
        s.setDeduction(deduction);
        s.setCreateDate(LocalDate.now());

        if (req.getTeacherId() != null) {
            Teacher t = teacherRepo.findById(req.getTeacherId()).orElseThrow(() -> new RuntimeException("Không tìm thấy giáo viên"));
            s.setTeacher(t);
            
            // Công thức: Tổng thực nhận = Lương cơ bản của giảng viên + Thưởng - Khấu trừ
            java.math.BigDecimal baseSalary = t.getSalary() != null ? t.getSalary() : java.math.BigDecimal.ZERO;
            java.math.BigDecimal totalSalary = baseSalary.add(bonus).subtract(deduction);
            s.setTotalSalary(totalSalary);
        } else {
            s.setTotalSalary(java.math.BigDecimal.ZERO);
        }
        
        return mapToDTO(salaryRepo.save(s));
    }

    // Chuyển Entity sang DTO
    private SalaryResponseDTO mapToDTO(Salary s) {
        SalaryResponseDTO dto = new SalaryResponseDTO();
        dto.setSalaryId(s.getSalaryId());
        dto.setMonth(s.getMonth());
        dto.setYear(s.getYear());
        dto.setTotalHours(s.getTotalHours());
        dto.setBonus(s.getBonus());
        dto.setDeduction(s.getDeduction());
        dto.setTotalSalary(s.getTotalSalary());
        dto.setCreateDate(s.getCreateDate());
        if(s.getTeacher() != null) dto.setTeacherName(s.getTeacher().getName());
        return dto;
    }

    @Override
    public void deleteSalary(String id) {
        salaryRepo.deleteById(id);
    }

    @Override
    public List<SalaryResponseDTO> searchSalaries(String keyword) {
        // Tìm kiếm các bảng lương theo tên giảng viên hoặc mã bảng lương chứa từ khóa
        List<Salary> list = salaryRepo.findByTeacher_NameContainingIgnoreCaseOrSalaryIdContainingIgnoreCase(keyword, keyword);
        List<SalaryResponseDTO> res = new ArrayList<>();
        for (Salary s : list) {
            res.add(mapToDTO(s));
        }
        return res;
    }
}
