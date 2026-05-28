package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    //Lấy toàn bộ danh sách giáo viên
    public List<Teacher> getAllteachers() {
        return teacherRepository.findAll();
    }

    //Update/Edit giáo viên

    //Thêm mới giáo viên
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    //Xóa giáo viên theo Id
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    //Tìm giáo viên theo Id
    public void findTeacher(Long id) {
        teacherRepository.findById(id);
    }

}
