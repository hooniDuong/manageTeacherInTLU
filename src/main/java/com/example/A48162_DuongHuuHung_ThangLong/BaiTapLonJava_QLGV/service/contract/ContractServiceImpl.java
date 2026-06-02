package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.contract;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Contract;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired private ContractRepository repo;

    @Override
    public List<Contract> getContractsByTeacherId(String teacherId) {
        return repo.findByTeacher_TeacherId(teacherId);
    }

    @Override
    public List<Contract> getAllContracts() {
        return repo.findAll();
    }

    @Override
    public Contract getContractById(String id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Lỗi không tìm thấy Hợp đồng"));
    }

    @Override
    public Contract createContract(Contract contract) {
        return repo.save(contract);
    }

    @Override
    public Contract updateContract(String id, Contract contract) {
        Contract c = repo.findById(id).orElseThrow(() -> new RuntimeException("Lỗi"));
        c.setContractType(contract.getContractType());
        c.setStartDate(contract.getStartDate());
        c.setEndDate(contract.getEndDate());
        c.setBaseSalary(contract.getBaseSalary());
        c.setStatus(contract.getStatus());
        c.setTeacher(contract.getTeacher());
        return repo.save(c);
    }

    @Override
    public void deleteContract(String id) {
        repo.deleteById(id);
    }

    @Override
    public List<Contract> searchContracts(String keyword) {
        // Tìm kiếm theo Loại hợp đồng, Mã hợp đồng, hoặc Tên giảng viên chứa từ khóa
        return repo.findByContractTypeContainingIgnoreCaseOrContractIdContainingIgnoreCaseOrTeacher_NameContainingIgnoreCase(keyword, keyword, keyword);
    }
}
