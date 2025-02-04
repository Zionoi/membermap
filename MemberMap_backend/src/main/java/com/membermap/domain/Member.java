// Lombok 어노테이션을 사용한 예제
package com.membermap.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "MEMBERS")
@Data    // @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode 등을 포함
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String purchasedProduct;
    private String region;
    private LocalDateTime registeredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;
}
