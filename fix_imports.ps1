$rootPath = "f:\BaiTapLonJava_QLGV\src\main\java\com\example\A48162_DuongHuuHung_ThangLong\BaiTapLonJava_QLGV"

$entities = @{
    "StudentClass" = "studentclass"
    "Role" = "role"
    "User" = "user"
    "Teacher" = "teacher"
    "TeachingAssignment" = "teachingAssignment"
    "Salary" = "salary"
    "Subject" = "subject"
    "Classroom" = "classroom"
    "Semester" = "semester"
    "Contract" = "contract"
    "TeachingLog" = "teachinglog"
    "Department" = "department"
    "Faculty" = "faculty"
}

# Update Role DTO packages
$roleFiles = @("$rootPath\dto\role\RoleRequestDTO.java", "$rootPath\dto\role\RoleResponseDTO.java")
foreach ($file in $roleFiles) {
    if (Test-Path $file) {
        $content = Get-Content $file -Raw
        $content = $content -replace "package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto;", "package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.role;"
        Set-Content -Path $file -Value $content
    }
}

# Search in Service, Controller
$files = Get-ChildItem -Path "$rootPath\service", "$rootPath\controller" -Recurse -Filter "*.java"

foreach ($file in $files) {
    $content = Get-Content $file.FullName -Raw
    $modified = $false

    foreach ($key in $entities.Keys) {
        $pkg = $entities[$key]
        $oldImportReq = "import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.${key}RequestDTO;"
        $newImportReq = "import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.${pkg}.${key}RequestDTO;"
        
        $oldImportRes = "import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.${key}ResponseDTO;"
        $newImportRes = "import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.${pkg}.${key}ResponseDTO;"

        if ($content -match \[Regex\]::Escape($oldImportReq)) {
            $content = $content -replace \[Regex\]::Escape($oldImportReq), $newImportReq
            $modified = $true
        }
        if ($content -match \[Regex\]::Escape($oldImportRes)) {
            $content = $content -replace \[Regex\]::Escape($oldImportRes), $newImportRes
            $modified = $true
        }
    }

    if ($modified) {
        Set-Content -Path $file.FullName -Value $content
    }
}

Write-Host "Done fixing imports."
