package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "users_id")
    private String usersId;

    private String username;

    private String password;

    private String email;

    private boolean status;

    private LocalDate createDate;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;


}
