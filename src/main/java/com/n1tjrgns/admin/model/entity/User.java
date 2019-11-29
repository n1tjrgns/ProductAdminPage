package com.n1tjrgns.admin.model.entity;

import com.n1tjrgns.admin.model.enumclass.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)

//아래 두 개는 롬복 어노테이션
@Builder //빌더패턴
//체이닝을 사용하겠다는 어노테이션
@Accessors(chain = true) //체인패턴
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    //status 변수 ENUM 형태로 리팩토링하기
    //고정적인 String 값에 대해서는 ENUM값으로 관리하는 것이 오타를 줄이는데도 도움이 되고 좋음
    @Enumerated(EnumType.STRING)
    private UserStatus status;  // REGISTERED / UNREGISTERED/ WAITING /

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    //User 는 1명이지만, 장바구니는 여러개 가질 수 있음 1:N
    //지연로딩, user라는 멤버변수에 매핑시킨다.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;
}
