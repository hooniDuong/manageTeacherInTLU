package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.classroom;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Classroom;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    @Autowired private ClassroomRepository repo;

    @Override
    public List<Classroom> getAllClassrooms() {
        return repo.findAll();
    }

    @Override
    public Classroom getClassroomById(String id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy phòng học"));
    }

    @Override
    public Classroom createClassroom(Classroom classroom) {
        return repo.save(classroom);
    }

    @Override
    public Classroom updateClassroom(String id, Classroom classroom) {
        Classroom c = repo.findById(id).orElseThrow(() -> new RuntimeException("Lỗi không tìm thấy phòng"));
        c.setName(classroom.getName());
        c.setCapacity(classroom.getCapacity());
        c.setBuilding(classroom.getBuilding());
        return repo.save(c);
    }

    @Override
    public void deleteClassroom(String id) {
        repo.deleteById(id);
    }

    @Override
    public List<Classroom> searchClassroomsByName(String name) {
        // Thực hiện tìm kiếm phòng học chứa từ khóa tên (không phân biệt hoa thường)
        return repo.findByNameContainingIgnoreCase(name);
    }
}
