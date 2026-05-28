package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository;


import org.springframework.data.jpa.repository.JpaRepository;

//Spring tự tạo INSERT, DELETE, UPDATE, SELECT
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
//    Đã có sẵn những:
//    save(); Thêm giáo viên
//    findAll(); Hiển thị toàn bộ giáo viên
//    findById(); Tìm kiếm theo Id
//    deleteById(); Xóa theo Id
    
}
