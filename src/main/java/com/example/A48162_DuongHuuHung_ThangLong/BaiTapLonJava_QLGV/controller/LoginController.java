package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.controller;

import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity.User;
import com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * LoginController — Xử lý Đăng nhập và Đăng xuất
 *
 * Luồng hoạt động:
 *   GET  /login  → Hiển thị trang đăng nhập
 *   POST /login  → Nhận username + password từ form
 *                  → Kiểm tra với database
 *                  → Nếu đúng: lưu User vào Session, chuyển về trang chủ
 *                  → Nếu sai: báo lỗi, ở lại trang login
 *   GET  /logout → Xóa Session, chuyển về trang login
 */
@Controller
public class LoginController {

    // Inject UserRepository để truy vấn bảng users trong database
    @Autowired
    private UserRepository userRepository;

    /**
     * GET /login
     * Chỉ hiển thị form đăng nhập (file login.html)
     * Nếu người dùng đã đăng nhập rồi thì chuyển về trang chủ luôn
     */
    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {
        // Kiểm tra xem trong session đã có user chưa
        // Nếu đã đăng nhập (session còn hạn) → không cần vào login nữa
        if (session.getAttribute("loggedUser") != null) {
            return "redirect:/";
        }
        // Chưa đăng nhập → hiển thị trang login.html
        return "login";
    }

    /**
     * POST /login
     * Nhận dữ liệu từ form đăng nhập (username + password)
     * Kiểm tra với database rồi xử lý
     *
     * @param username  Tên đăng nhập người dùng nhập
     * @param password  Mật khẩu người dùng nhập
     * @param session   HttpSession dùng để lưu thông tin đăng nhập
     * @param model     Dùng để truyền thông báo lỗi về giao diện
     */
    @PostMapping("/login")
    public String processLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            Model model
    ) {
        // Bước 1: Tìm user trong database CHỈ theo username
        // Dùng findByUsername thay vì findByUsernameAndPassword để tránh lỗi
        // NonUniqueResultException khi database có bản ghi trùng lặp
        Optional<User> userOpt = userRepository.findFirstByUsername(username);

        // Bước 2: Kiểm tra kết quả — username có tồn tại không?
        if (userOpt.isEmpty()) {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            return "login";
        }

        User loggedUser = userOpt.get();

        // Bước 3: So sánh password thủ công trong Java
        // Dùng equals() để so sánh chuỗi, không phân biệt hoa thường với equalsIgnoreCase
        if (!loggedUser.getPassword().equals(password)) {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            return "login";
        }

        // Bước 4: Kiểm tra tài khoản có đang hoạt động không
        // (trường status = true là hoạt động, false là bị khóa)
        if (!loggedUser.isStatus()) {
            model.addAttribute("error", "Tài khoản của bạn đã bị khóa. Vui lòng liên hệ quản trị viên!");
            return "login";
        }

        // Bước 4: Đăng nhập thành công → lưu thông tin user vào Session
        // Session là bộ nhớ tạm trên server, gắn với trình duyệt của người dùng
        // Mỗi lần vào trang mới, server sẽ đọc session để biết ai đang đăng nhập
        session.setAttribute("loggedUser", loggedUser);

        // Bước 5: Chuyển hướng về trang chủ
        return "redirect:/";
    }

    /**
     * GET /logout
     * Xóa Session → người dùng bị đăng xuất
     * Sau đó chuyển về trang login
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate() xóa toàn bộ Session hiện tại (xóa thông tin đăng nhập)
        session.invalidate();
        // Chuyển về trang login
        return "redirect:/login";
    }
}
