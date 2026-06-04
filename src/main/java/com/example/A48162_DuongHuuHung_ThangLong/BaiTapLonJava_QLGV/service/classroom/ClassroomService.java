package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.classroom;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Classroom;
import java.util.List;


public interface ClassroomService {
    // Lấy danh sách tất cả phòng học (Phòng 301, 302...)
    List<Classroom> getAllClassrooms();
    
    // Lấy thông tin sức chứa, vị trí của 1 phòng
    Classroom getClassroomById(String id);
    
    // Thêm phòng học mới vào hệ thống
    Classroom createClassroom(Classroom classroom);
    
    // Sửa thông tin phòng
    Classroom updateClassroom(String id, Classroom classroom);
    
    // Xóa phòng
    void deleteClassroom(String id);

    // Tìm kiếm phòng học theo tên
    List<Classroom> searchClassroomsByName(String name);
}
