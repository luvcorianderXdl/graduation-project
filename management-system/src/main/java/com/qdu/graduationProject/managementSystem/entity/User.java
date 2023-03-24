package com.qdu.graduationProject.managementSystem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xdl
 * @date 2023/3/23 16:59
 */
@Getter
@Setter
@NoArgsConstructor
@Table(name = "u_user")
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //待完善
}
