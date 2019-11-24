package com.n1tjrgns.admin.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//계속해서 바뀌는 data 부분을 정의할 때는 주로 제네릭 타입을 많이 사용한다.
public class Header<T> {

    //api 통신 시간
    //프론트와 통신 할 때는 String
    //api는 주로 스네이크 케이스를 사용하기 때문에 카멜케이스는 수정해줘야한다.
    //JsonProperty("transaction_time") 이렇게 일일이 다 바꿔주기는 무리기 때문에 properties 파일을 수정한다.
    private LocalDateTime transactionTime;

    //api 응답 코드
    private String resultCode;

    //api 부가 설명
    private String description;

    private T data;

    //정상적 통신 : OK
    public static <T> Header<T> OK(){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }


    //정상 data OK
    public static <T> Header<T> OK(T data){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    //ERROR
    public static <T> Header<T> ERROR(String description){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                //에러 내용은 받아서 보여주기
                .description(description)
                .build();
    }

}
