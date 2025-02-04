package com.membermap.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자 고유 아이디 (예: username)
    @Column(unique = true, nullable = false)
    private String username;

    private String password;
    private String email;

    // User와 Member는 일대다 관계
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members;
}
