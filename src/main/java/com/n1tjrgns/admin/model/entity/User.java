package com.n1tjrgns.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

// @Table(name="user") 테이블의 이름과 클래스명이 같으면 해당 어노테이션은 사용하지 않아도됨
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

//oneTomany나 join 처럼 상호 참조시 lombok에서 toString을 계속 찍어 오버플로우가 발생한다.
//제외시키는 방법
@ToString(exclude = {"orderGroup"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    private String status;  // REGISTERED / UNREGISTERED/ WAITING /

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    //User 는 1명이지만, 장바구니는 여러개 가질 수 있음 1:N
    //지연로딩, user라는 멤버변수에 매핑시킨다.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;
}
