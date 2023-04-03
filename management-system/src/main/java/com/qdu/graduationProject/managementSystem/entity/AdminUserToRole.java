package com.qdu.graduationProject.managementSystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author xdl
 * @date 2023/4/3 14:46
 */
@Getter
@Setter
@Table(name = "admin_user_to_role")
@Entity
public class AdminUserToRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "admin_user_id")
    private Long adminUserId;

    @Column(name = "role_id")
    private Long roleId;
}
