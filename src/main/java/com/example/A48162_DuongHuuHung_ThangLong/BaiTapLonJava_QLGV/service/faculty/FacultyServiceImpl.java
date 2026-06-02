package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.faculty;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.faculty.FacultyRequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.faculty.FacultyResponseDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Faculty;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.FacultyRepository;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.faculty.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public List<FacultyResponseDTO> getAllFaculties() {
        // 1. Lấy tất cả nguyên liệu thô từ Tủ lạnh (Database)
        List<Faculty> faculties = facultyRepository.findAll();

        // 2. Tạo một cái mâm (List) để chứa các đĩa thức ăn (DTO)
        List<FacultyResponseDTO> responseList = new ArrayList<>();

        // 3. Xử lý từng nguyên liệu (Entity) thành đĩa thức ăn (DTO)
        for (Faculty f : faculties) {
            FacultyResponseDTO dto = new FacultyResponseDTO();
            dto.setFacultyId(f.getFacultyId());
            dto.setName(f.getName());
            dto.setDescription(f.getDescription());

            responseList.add(dto); // Bưng lên mâm
        }

        return responseList;
    }

    @Override
    public FacultyResponseDTO getFacultyById(String id) {
        // Tìm Khoa trong DB. Nếu không có thì ném ra lỗi (Exception)
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Khoa với ID: " + id));

        // Bày ra đĩa (DTO)
        FacultyResponseDTO dto = new FacultyResponseDTO();
        dto.setFacultyId(faculty.getFacultyId());
        dto.setName(faculty.getName());
        dto.setDescription(faculty.getDescription());

        return dto;
    }

    @Override
    public FacultyResponseDTO createFaculty(FacultyRequestDTO request) {
        // 1. Biến phiếu Order thành nguyên liệu thô (Entity)
        Faculty faculty = new Faculty();
        faculty.setFacultyId(request.getFacultyId());
        faculty.setName(request.getName());
        faculty.setDescription(request.getDescription());

        // 2. Cất vào tủ lạnh (Save)
        Faculty savedFaculty = facultyRepository.save(faculty);

        // 3. Trả kết quả ra đĩa (DTO)
        FacultyResponseDTO responseDTO = new FacultyResponseDTO();
        responseDTO.setFacultyId(savedFaculty.getFacultyId());
        responseDTO.setName(savedFaculty.getName());
        responseDTO.setDescription(savedFaculty.getDescription());

        return responseDTO;
    }

    @Override
    public FacultyResponseDTO updateFaculty(String id, FacultyRequestDTO request) {
        // 1. Lấy nguyên liệu cũ trong Tủ lạnh ra kiểm tra
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Khoa với ID: " + id));

        // 2. Cập nhật thông tin mới (Chú ý: không cập nhật ID)
        faculty.setName(request.getName());
        faculty.setDescription(request.getDescription());

        // 3. Lưu đè vào tủ lạnh
        Faculty updatedFaculty = facultyRepository.save(faculty);

        // 4. Bày ra đĩa (DTO) trả về cho người dùng
        FacultyResponseDTO responseDTO = new FacultyResponseDTO();
        responseDTO.setFacultyId(updatedFaculty.getFacultyId());
        responseDTO.setName(updatedFaculty.getName());
        responseDTO.setDescription(updatedFaculty.getDescription());

        return responseDTO;
    }

    @Override
    public void deleteFaculty(String id) {
        // Kiểm tra xem Khoa có tồn tại không
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Khoa với ID: " + id));

        // Xóa khỏi DB
        facultyRepository.delete(faculty);
    }

    @Override
    public List<FacultyResponseDTO> searchFacultiesByName(String name) {
        // Tìm kiếm Khoa từ Database
        List<Faculty> faculties = facultyRepository.findByNameContainingIgnoreCase(name);
        List<FacultyResponseDTO> responseList = new ArrayList<>();
        for (Faculty f : faculties) {
            FacultyResponseDTO dto = new FacultyResponseDTO();
            dto.setFacultyId(f.getFacultyId());
            dto.setName(f.getName());
            dto.setDescription(f.getDescription());
            responseList.add(dto);
        }
        return responseList;
    }
}
