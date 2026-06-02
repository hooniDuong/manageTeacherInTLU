package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig — Cấu hình Web MVC
 *
 * File này dùng để đăng ký các Interceptor vào Spring MVC.
 * Không có file này thì AuthInterceptor sẽ không hoạt động dù đã tạo.
 *
 * @Configuration  → Báo cho Spring biết đây là file cấu hình
 * WebMvcConfigurer → Interface cho phép tùy chỉnh cấu hình Spring MVC
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Inject AuthInterceptor đã tạo ở file AuthInterceptor.java
    @Autowired
    private AuthInterceptor authInterceptor;

    /**
     * addInterceptors() — Đăng ký các Interceptor vào hệ thống
     *
     * Ở đây ta đăng ký AuthInterceptor để chặn TẤT CẢ request ("/**")
     * NGOẠI TRỪ trang login ("/login") — vì nếu chặn luôn /login
     * thì người dùng sẽ không bao giờ vào được trang đăng nhập!
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                // Áp dụng cho TẤT CẢ các URL
                .addPathPatterns("/**")
                // LOẠI TRỪ các URL sau (không cần đăng nhập):
                .excludePathPatterns(
                        "/login",   // Trang đăng nhập
                        "/logout"   // Trang đăng xuất (cần truy cập để xóa session)
                );
    }
}
