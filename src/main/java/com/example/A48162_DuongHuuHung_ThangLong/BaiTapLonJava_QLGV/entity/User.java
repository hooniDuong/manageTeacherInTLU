package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;

    private String username;

    private String password;

    private String email;

    private boolean status;

    private LocalDate createDate;

    //@OneToOne -> @ManyToOne
    //Nhiều User có thể chia sẻ chung 1 Role (VD: nhiều người có quyền TEACHER)
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


}
