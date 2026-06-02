package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.contract;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Contract;
import java.util.List;

/**
 * Service xử lý các nghiệp vụ liên quan đến HỢP ĐỒNG LAO ĐỘNG (Contract)
 */
public interface ContractService {
    // Lấy danh sách lịch sử Hợp đồng của một Giáo viên (Biết được họ từng ký hợp đồng gì)
    List<Contract> getContractsByTeacherId(String teacherId);
    
    List<Contract> getAllContracts();
    
    // Xem chi tiết 1 bản hợp đồng
    Contract getContractById(String id);
    
    // Ký hợp đồng mới với Giáo viên
    Contract createContract(Contract contract);
    
    // Sửa thông tin hợp đồng (Gia hạn, đổi mức lương cơ bản)
    Contract updateContract(String id, Contract contract);
    
    // Hủy hợp đồng
    void deleteContract(String id);

    // Tìm kiếm hợp đồng theo từ khóa
    List<Contract> searchContracts(String keyword);
}
