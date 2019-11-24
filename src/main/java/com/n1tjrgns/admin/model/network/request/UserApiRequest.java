package com.n1tjrgns.admin.model.network.request;

//요청과 응답에 대해 다른 값을 내려주는 경우가 생기기 때문에 request, response 나눠서 작성
//ex ) 비밀번호 암호화

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiRequest {

    private Long id;

    private String account;

    private String password;

    private String status;

    private String email;

    private String phoneNumber;
}