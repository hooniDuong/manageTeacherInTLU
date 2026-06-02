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

$files = Get-ChildItem -Path "$rootPath\service", "$rootPath\controller" -Recurse -Filter "*.java"

foreach ($file in $files) {
    $content = Get-Content $file.FullName -Raw
    $original = $content

    foreach ($key in $entities.Keys) {
        $pkg = $entities[$key]
        $oldImportReq = "import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.${key}RequestDTO;"
        $newImportReq = "import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.${pkg}.${key}RequestDTO;"
        
        $oldImportRes = "import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.${key}ResponseDTO;"
        $newImportRes = "import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.dto.${pkg}.${key}ResponseDTO;"

        $content = $content.Replace($oldImportReq, $newImportReq)
        $content = $content.Replace($oldImportRes, $newImportRes)
    }

    if ($content -cne $original) {
        Set-Content -Path $file.FullName -Value $content
        Write-Host "Modified $($file.FullName)"
    }
}
