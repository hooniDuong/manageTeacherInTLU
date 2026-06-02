package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.component;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Department;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Faculty;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.Role;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.User;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.DepartmentRepository;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.FacultyRepository;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.RoleRepository;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

// @Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void run(String... args) throws Exception {
        // Khởi tạo Role nếu chưa có
        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setRoleId("ADMIN");
            adminRole.setRoleName("Quản trị viên");
            roleRepository.save(adminRole);

            Role teacherRole = new Role();
            teacherRole.setRoleId("TEACHER");
            teacherRole.setRoleName("Giảng viên");
            roleRepository.save(teacherRole);
            
            System.out.println("Đã khởi tạo dữ liệu mẫu cho bảng Role!");
        }

        // Khởi tạo User admin nếu chưa có
        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.findById("ADMIN").orElse(null);
            if (adminRole != null) {
                User admin = new User();
                admin.setUserId(UUID.randomUUID().toString());
                admin.setUsername("admin");
                admin.setPassword("123456"); // Thực tế nên mã hóa
                admin.setEmail("admin@thanglong.edu.vn");
                admin.setRole(adminRole);
                admin.setStatus(true);
                admin.setCreateDate(LocalDate.now());
                userRepository.save(admin);
                
                System.out.println("Đã khởi tạo dữ liệu mẫu cho bảng User (Tài khoản: admin / Mật khẩu: 123456)!");
            }
        }

        // Khởi tạo Faculty (Khoa) mẫu nếu chưa có
        if (facultyRepository.count() == 0) {
            Faculty it = new Faculty();
            it.setFacultyId("CNTT");
            it.setName("Khoa Công nghệ thông tin");
            it.setDescription("Chuyên đào tạo lập trình viên và kỹ sư phần mềm");
            facultyRepository.save(it);

            Faculty kt = new Faculty();
            kt.setFacultyId("KT");
            kt.setName("Khoa Kinh tế");
            kt.setDescription("Đào tạo khối ngành kinh tế, kế toán, tài chính");
            facultyRepository.save(kt);
            
            System.out.println("Đã khởi tạo dữ liệu mẫu cho bảng Faculty!");
        }

        // Khởi tạo Department (Bộ môn) mẫu nếu chưa có
        if (departmentRepository.count() == 0) {
            Faculty it = facultyRepository.findById("CNTT").orElse(null);
            if (it != null) {
                Department khmt = new Department();
                khmt.setDepartmentId("KHMT");
                khmt.setName("Khoa học máy tính");
                khmt.setLocationOffice("Phòng 201 - Tòa A");
                khmt.setFaculty(it);
                khmt.setDescription("Bộ môn KHMT");
                departmentRepository.save(khmt);

                Department httt = new Department();
                httt.setDepartmentId("HTTT");
                httt.setName("Hệ thống thông tin");
                httt.setLocationOffice("Phòng 202 - Tòa A");
                httt.setFaculty(it);
                httt.setDescription("Bộ môn HTTT");
                departmentRepository.save(httt);
                
                System.out.println("Đã khởi tạo dữ liệu mẫu cho bảng Department!");
            }
        }
    }
}
