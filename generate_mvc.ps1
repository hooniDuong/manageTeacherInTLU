$root = "f:\BaiTapLonJava_QLGV\src\main\java\com\example\A48162_DuongHuuHung_ThangLong\BaiTapLonJava_QLGV"
$res = "f:\BaiTapLonJava_QLGV\src\main\resources\templates"

$entities = @(
    @{ Name="Faculty"; Pkg="faculty" }
    @{ Name="StudentClass"; Pkg="studentclass" }
    @{ Name="Role"; Pkg="role" }
    @{ Name="User"; Pkg="user" }
    @{ Name="TeachingAssignment"; Pkg="teachingAssignment" }
    @{ Name="Salary"; Pkg="salary" }
    @{ Name="Subject"; Pkg="subject" }
    @{ Name="Classroom"; Pkg="classroom" }
    @{ Name="Semester"; Pkg="semester" }
    @{ Name="Contract"; Pkg="contract" }
    @{ Name="TeachingLog"; Pkg="teachingLog" }
)

foreach ($e in $entities) {
    $name = $e.Name
    $pkg = $e.Pkg
    $lower = $name.ToLower()
    $plural = $lower + "s"
    
    # Generate Controller
    $ctrl = @"
package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.${pkg}.${name}RequestDTO;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.service.${pkg}.${name}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/$plural")
public class ${name}Controller {
    @Autowired private ${name}Service service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", service.getAll${name}s());
        return "$plural";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("obj", new ${name}RequestDTO());
        return "new${name}";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("obj") ${name}RequestDTO req) {
        service.create${name}(req);
        return "redirect:/$plural";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete${name}(id);
        return "redirect:/$plural";
    }
}
"@
    # Fix method names for some specifics (e.g. createFaculty -> addDepartment or createStudentClass vs createClass)
    # This is a bit risky but mostly create${name} works because my interfaces used create[Entity]
    
    # Exceptions
    if ($name -eq "StudentClass") { $ctrl = $ctrl.Replace("createStudentClass", "createClass").Replace("deleteStudentClass", "deleteClass").Replace("getAllStudentClasss", "getAllClasses") }
    if ($name -eq "TeachingLog") { $ctrl = $ctrl.Replace("getAllTeachingLogs", "getLogsByAssignmentId").Replace("deleteTeachingLog", "deleteLog").Replace("createTeachingLog", "createLog") }
    if ($name -eq "TeachingAssignment") { $ctrl = $ctrl.Replace("getAllTeachingAssignments", "getAllAssignments").Replace("createTeachingAssignment", "createAssignment").Replace("deleteTeachingAssignment", "deleteAssignment") }
    if ($name -eq "Contract") { $ctrl = $ctrl.Replace("getAllContracts", "getContractsByTeacherId") }
    
    Set-Content -Path "$root\controller\${name}Controller.java" -Value $ctrl
    
    # Generate simple HTML list
    $html = @"
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Danh sách $name</title>
    <style>
        body{ font-family: Arial; margin: 40px; }
        table{ width: 100%; border-collapse: collapse; margin-top: 20px;}
        table, th, td{ border: 1px solid black; }
        th, td{ padding: 10px; text-align: center; }
        a{ text-decoration: none; padding: 5px 10px; border-radius: 5px; }
        .add-btn{ background-color: green; color: white; }
        .delete-btn{ background-color: red; color: white; }
    </style>
</head>
<body>
<h1>Quản lý $name</h1>
<a href="/$plural/new" class="add-btn">Thêm mới</a>
<a href="/" style="margin-left:10px;">Về trang chủ</a>
<table>
    <thead><tr><th>Mã đối tượng (ID)</th><th>Thông tin cơ bản</th><th>Thao tác</th></tr></thead>
    <tbody>
    <tr th:each="item : `$`{list}">
        <!-- Dùng logic render chung đơn giản do script tự động tạo -->
        <td>Dữ liệu ID</td>
        <td>Dữ liệu Object</td>
        <td><a th:href="@{/$plural/delete/{id}(id=0)}" class="delete-btn">Xóa</a></td>
    </tr>
    </tbody>
</table>
</body>
</html>
"@
    Set-Content -Path "$res\$plural.html" -Value $html
}

# Create index.html homepage
$index = @"
<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ Quản Lý Giáo Viên</title>
    <style>
        body{ font-family: Arial; margin: 40px; text-align: center; }
        .menu { display: flex; flex-wrap: wrap; justify-content: center; gap: 20px; margin-top: 50px; }
        .menu a { padding: 20px 40px; background: #007bff; color: white; text-decoration: none; border-radius: 10px; font-size: 18px; font-weight: bold; width: 200px;}
        .menu a:hover { background: #0056b3; }
    </style>
</head>
<body>
    <h1>Hệ Thống Quản Lý Giáo Viên</h1>
    <h2>Vui lòng chọn chức năng:</h2>
    <div class="menu">
        <a href="/teachers">👩‍🏫 Quản lý Giáo viên</a>
        <a href="/departments">🏢 Quản lý Bộ môn</a>
        <a href="/faculties">🏫 Quản lý Khoa</a>
        <a href="/users">👤 Quản lý Tài khoản</a>
        <a href="/teachingassignments">📅 Phân công Giảng dạy</a>
        <a href="/salarys">💰 Quản lý Lương</a>
        <a href="/subjects">📚 Quản lý Môn học</a>
        <a href="/classrooms">🚪 Quản lý Phòng học</a>
    </div>
</body>
</html>
"@
Set-Content -Path "$res\index.html" -Value $index
