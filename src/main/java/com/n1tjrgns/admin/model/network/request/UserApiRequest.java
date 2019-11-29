package com.n1tjrgns.admin.model.network.request;

//요청과 응답에 대해 다른 값을 내려주는 경우가 생기기 때문에 request, response 나눠서 작성
//ex ) 비밀번호 암호화

import com.n1tjrgns.admin.model.enumclass.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiRequest {

    private Long id;

    private String account;

    private String password;

    //Enum 형태로 변경
    private UserStatus status;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;
}
