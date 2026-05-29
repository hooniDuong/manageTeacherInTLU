package com.example.A48162_DuongHuuHung_ThangLong.BaiTapLonJava_QLGV.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.core.SpringVersion;

@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "role_id")
    private String roleId;

    private String roleName;
}
