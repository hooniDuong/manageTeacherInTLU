-- Disable foreign key checks for clean insertions
SET FOREIGN_KEY_CHECKS = 0;

-- 1. ROLE (Vai trò)
INSERT IGNORE INTO `role` (`role_id`, `role_name`) VALUES
('ADMIN', 'Quản trị viên'),
('TEACHER', 'Giảng viên');
-- 2. USERS (Tài khoản người dùng)
INSERT IGNORE INTO `users` (`user_id`, `username`, `password`, `email`, `status`, `create_date`, `role_id`) VALUES
('USR001', 'admin', '123456', 'admin@thanglong.edu.vn', 1, '2026-01-01', 'ADMIN'),
('USR002', 'hungdh', '123456', 'hungdh@thanglong.edu.vn', 1, '2026-01-10', 'TEACHER'),
('USR003', 'lannt', '123456', 'lannt@thanglong.edu.vn', 1, '2026-01-12', 'TEACHER'),
('USR004', 'namph', '123456', 'namph@thanglong.edu.vn', 1, '2026-02-01', 'ACADEMIC'),
('USR005', 'minhha', '123456', 'minhha@thanglong.edu.vn', 1, '2026-02-15', 'TEACHER');

-- 3. FACULTY (Khoa)
INSERT IGNORE INTO `faculty` (`faculty_id`, `name`, `description`) VALUES
('CNTT', 'Khoa Công nghệ thông tin', 'Chuyên đào tạo kỹ sư phần mềm, an toàn thông tin và khoa học máy tính'),
('KT', 'Khoa Kinh tế - Quản lý', 'Đào tạo quản trị kinh doanh, kế toán, tài chính ngân hàng'),
('KHUD', 'Khoa Khoa học ứng dụng', 'Đào tạo toán ứng dụng, vật lý kỹ thuật');

-- 4. DEPARTMENT (Bộ môn thuộc Khoa)
INSERT IGNORE INTO `department` (`department_id`, `name`, `description`, `location_office`, `faculty_id`) VALUES
('KHMT', 'Khoa học máy tính', 'Bộ môn Khoa học máy tính', 'Phòng 201 - Tòa A', 'CNTT'),
('HTTT', 'Hệ thống thông tin', 'Bộ môn Hệ thống thông tin', 'Phòng 202 - Tòa A', 'CNTT'),
('KTPM', 'Kỹ thuật phần mềm', 'Bộ môn Kỹ thuật phần mềm', 'Phòng 203 - Tòa A', 'CNTT'),
('QTKD', 'Quản trị kinh doanh', 'Bộ môn Quản trị kinh doanh', 'Phòng 301 - Tòa B', 'KT'),
('TCNH', 'Tài chính ngân hàng', 'Bộ môn Tài chính ngân hàng', 'Phòng 302 - Tòa B', 'KT'),
('MATH', 'Toán ứng dụng', 'Bộ môn Toán học ứng dụng', 'Phòng 401 - Tòa C', 'KHUD');

-- 5. TEACHER (Giảng viên)
INSERT IGNORE INTO `teacher` (`teacher_id`, `name`, `gender`, `birthday`, `phone`, `email`, `address`, `degree`, `position`, `hire_date`, `salary`, `status`, `avatar`, `department_id`, `users_id`) VALUES
('GV001', 'Dương Hữu Hùng', 'Nam', '1985-05-15', '0912345678', 'hungdh@thanglong.edu.vn', 'Đống Đa, Hà Nội', 'Tiến sĩ', 'Trưởng bộ môn', '2015-09-01', 25000000.00, 'Đang làm việc', 'avatar_hung.jpg', 'KTPM', 'USR002'),
('GV002', 'Nguyễn Thị Lan', 'Nữ', '1990-08-22', '0987654321', 'lannt@thanglong.edu.vn', 'Cầu Giấy, Hà Nội', 'Thạc sĩ', 'Giảng viên cơ hữu', '2018-10-01', 18000000.00, 'Đang làm việc', 'avatar_lan.jpg', 'HTTT', 'USR003'),
('GV003', 'Trần Văn Minh', 'Nam', '1988-12-05', '0901234567', 'minhha@thanglong.edu.vn', 'Hai Bà Trưng, Hà Nội', 'Thạc sĩ', 'Giảng viên cơ hữu', '2020-03-15', 16500000.00, 'Đang làm việc', 'avatar_minh.jpg', 'KHMT', 'USR005'),
('GV004', 'Phạm Hồng Thái', 'Nam', '1978-04-10', '0934567890', 'thaiph@thanglong.edu.vn', 'Thanh Xuân, Hà Nội', 'Phó Giáo sư', 'Giảng viên cao cấp', '2010-01-15', 35000000.00, 'Đang làm việc', 'avatar_thai.jpg', 'KHMT', NULL),
('GV005', 'Lê Thu Trang', 'Nữ', '1993-11-30', '0978901234', 'tranglt@thanglong.edu.vn', 'Hoàng Mai, Hà Nội', 'Thạc sĩ', 'Giảng viên thỉnh giảng', '2022-09-01', 12000000.00, 'Đang làm việc', 'avatar_trang.jpg', 'QTKD', NULL);

-- 6. SEMESTER (Học kỳ)
INSERT IGNORE INTO `semester` (`semester_id`, `name`, `year`, `start_date`, `end_date`) VALUES
('HK1_2025', 'Học kỳ I', 2025, '2025-09-01', '2026-01-15'),
('HK2_2025', 'Học kỳ II', 2025, '2026-02-01', '2026-06-15'),
('HK1_2026', 'Học kỳ I', 2026, '2026-09-01', '2027-01-15');

-- 7. CLASSROOM (Phòng học)
INSERT IGNORE INTO `classroom` (`classroom_id`, `name`, `building`, `capacity`) VALUES
('A101', 'Phòng học 101', 'Tòa nhà A', 60),
('A201', 'Phòng học 201', 'Tòa nhà A', 80),
('B302', 'Phòng máy tính 302', 'Tòa nhà B', 50),
('B405', 'Hội trường 405', 'Tòa nhà B', 120),
('C102', 'Phòng học 102', 'Tòa nhà C', 45);

-- 8. SUBJECT (Môn học)
INSERT IGNORE INTO `subject` (`subject_id`, `credits`, `description`, `department_id`) VALUES
('TIN101', 3, 'Lập trình hướng đối tượng với Java', 'KTPM'),
('TIN202', 4, 'Cơ sở dữ liệu quan hệ', 'HTTT'),
('TIN303', 3, 'Trí tuệ nhân tạo cơ bản', 'KHMT'),
('MGT101', 3, 'Quản trị học đại cương', 'QTKD'),
('MAT102', 4, 'Giải tích 1', 'MATH');

-- 9. STUDENT_CLASS (Lớp sinh viên hành chính)
INSERT IGNORE INTO `student_class` (`class_id`, `name`, `major`, `enrollment_year`, `faculty_id`) VALUES
('LH001', 'K23_CNTT1', 'Công nghệ thông tin', 2023, 'CNTT'),
('LH002', 'K23_CNTT2', 'Kỹ thuật phần mềm', 2023, 'CNTT'),
('LH003', 'K24_QTKD1', 'Quản trị kinh doanh', 2024, 'KT'),
('LH004', 'K25_TOAN1', 'Toán ứng dụng', 2025, 'KHUD');

-- 10. TEACHING_ASSIGNMENT (Phân công giảng dạy)
INSERT IGNORE INTO `teaching_assignment` (`assignment_id`, `hours`, `schedule`, `start_date`, `end_date`, `status`, `classroom_id`, `student_class_id`, `teacher_id`, `subject_id`, `semester_id`) VALUES
('PC001', 45, 'Thứ 2 (Tiết 1-3)', '2026-02-05', '2026-06-10', 'Đang tiến hành', 'A101', 'LH001', 'GV001', 'TIN101', 'HK2_2025'),
('PC002', 60, 'Thứ 4 (Tiết 5-8)', '2026-02-06', '2026-06-12', 'Đang tiến hành', 'B302', 'LH002', 'GV002', 'TIN202', 'HK2_2025'),
('PC003', 45, 'Thứ 6 (Tiết 1-3)', '2026-02-07', '2026-06-11', 'Đang tiến hành', 'A201', 'LH001', 'GV003', 'TIN303', 'HK2_2025'),
('PC004', 45, 'Thứ 3 (Tiết 4-6)', '2026-02-05', '2026-06-09', 'Đang tiến hành', 'C102', 'LH003', 'GV005', 'MGT101', 'HK2_2025');

-- 11. CONTRACT (Hợp đồng giáo viên)
INSERT IGNORE INTO `contract` (`contract_id`, `contract_type`, `start_date`, `end_date`, `base_salary`, `teacher_id`, `status`) VALUES
('HD001', 'Cơ hữu dài hạn', '2015-09-01', '2030-09-01', 25000000.00, 'GV001', 'Hiệu lực'),
('HD002', 'Cơ hữu 3 năm', '2018-10-01', '2027-10-01', 18000000.00, 'GV002', 'Hiệu lực'),
('HD003', 'Thỉnh giảng theo kỳ', '2022-09-01', '2026-07-01', 12000000.00, 'GV005', 'Hiệu lực');

-- 12. SALARY (Bảng lương tháng)
INSERT IGNORE INTO `salary` (`salary_id`, `month`, `year`, `total_hours`, `bonus`, `deduction`, `total_salary`, `create_date`, `teacher_id`) VALUES
('L2026_05_01', 5, 2026, 40.0, 2000000.00, 500000.00, 26500000.00, '2026-05-31', 'GV001'),
('L2026_05_02', 5, 2026, 45.0, 1500000.00, 200000.00, 19300000.00, '2026-05-31', 'GV002'),
('L2026_05_03', 5, 2026, 38.0, 0.00, 0.00, 16500000.00, '2026-05-31', 'GV003');

-- 13. TEACHING_LOG (Nhật ký giảng dạy từng buổi)
INSERT IGNORE INTO `teaching_log` (`log_id`, `teaching_date`, `hours_taught`, `topic`, `status`, `assignment_id`) VALUES
('LOG001', '2026-02-09', 3.0, 'Giới thiệu môn học OOP và cài đặt JDK', 'Đã dạy', 'PC001'),
('LOG002', '2026-02-16', 3.0, 'Lớp và Đối tượng trong Java', 'Đã dạy', 'PC001'),
('LOG003', '2026-02-11', 4.0, 'Tổng quan về Hệ quản trị CSDL quan hệ', 'Đã dạy', 'PC002'),
('LOG004', '2026-02-18', 4.0, 'Ngôn ngữ định nghĩa dữ liệu DDL', 'Đã dạy', 'PC002'),
('LOG005', '2026-02-13', 3.0, 'Mạng Neural nhân tạo sơ khai', 'Đã dạy', 'PC003'),
('LOG006', '2026-05-25', 3.0, 'Tổng kết ôn tập chuẩn bị thi cuối kỳ', 'Đã dạy', 'PC001');

-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;
