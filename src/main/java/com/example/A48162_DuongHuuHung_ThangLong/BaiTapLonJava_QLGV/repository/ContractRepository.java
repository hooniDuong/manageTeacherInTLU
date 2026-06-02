package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, String> {
    //Tìm hợp đồng lao động của giáo viên nào đó cụ thể
    List<Contract> findByTeacher_TeacherId(String teacherId);

    // Tìm kiếm hợp đồng theo Loại hợp đồng, Mã hợp đồng, hoặc Tên giảng viên (không phân biệt hoa thường)
    List<Contract> findByContractTypeContainingIgnoreCaseOrContractIdContainingIgnoreCaseOrTeacher_NameContainingIgnoreCase(String contractType, String contractId, String teacherName);
}
