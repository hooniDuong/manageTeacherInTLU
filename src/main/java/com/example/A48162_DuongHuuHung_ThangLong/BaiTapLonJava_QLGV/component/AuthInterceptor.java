package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.component;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * AuthInterceptor — Bộ lọc xác thực (Authentication Interceptor)
 *
 * Interceptor là lớp "chặn" nằm giữa trình duyệt và Controller.
 * Mỗi khi có request đến server, Interceptor chạy TRƯỚC khi Controller xử lý.
 *
 * Nhiệm vụ của AuthInterceptor:
 *   1. Kiểm tra Session xem người dùng đã đăng nhập chưa
 *   2. Nếu chưa → chuyển về /login (không cho vào)
 *   3. Nếu rồi → truyền thông tin user (tên, role) vào request để Thymeleaf dùng
 *
 * Được đăng ký trong WebConfig.java để áp dụng cho TẤT CẢ các URL (trừ /login)
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    /**
     * preHandle() chạy TRƯỚC khi Controller xử lý request.
     * Trả về true  → cho phép request đi tiếp vào Controller
     * Trả về false → chặn lại, không vào Controller nữa
     *
     * @param request  Thông tin về HTTP request (URL, method, session, ...)
     * @param response Dùng để redirect (chuyển hướng) nếu cần
     * @param handler  Controller sẽ xử lý request này (không cần dùng ở đây)
     */
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        // Bước 1: Lấy session hiện tại
        // false = không tạo session mới nếu chưa có (tránh lãng phí tài nguyên)
        HttpSession session = request.getSession(false);

        // Bước 2: Kiểm tra xem session có tồn tại và có chứa thông tin user không
        boolean isLoggedIn = (session != null && session.getAttribute("loggedUser") != null);

        if (!isLoggedIn) {
            // Chưa đăng nhập → chuyển hướng về trang /login
            response.sendRedirect("/login");
            // Trả về false để dừng lại, không vào Controller
            return false;
        }

        // Bước 3: Đã đăng nhập → lấy thông tin user từ session
        User loggedUser = (User) session.getAttribute("loggedUser");

        // Bước 4: Truyền thông tin user vào request attribute
        // userRole = roleId (ví dụ: "ADMIN", "TEACHER") dùng để kiểm tra quyền trong HTML
        // Dùng getRoleId() thay vì getRoleName() vì HTML check th:if="${userRole == 'ADMIN'}"
        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("userRole", loggedUser.getRole().getRoleId());

        // Bước 5: Cho phép request tiếp tục vào Controller
        return true;
    }
}
