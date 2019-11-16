package com.n1tjrgns.admin.repository;

import com.n1tjrgns.admin.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //select * from user where account = ~
    //매개변수명은 실제 칼럼명과 달라도 메소드명만 맞으면 상관없음, 암거나 가능
    /*Optional<User> findByAccount (String account);

    Optional<User> findByAccountAndEmail(String account, String email);*/

    //같은 번호로 여러명이 가입할 수 있도록
    //firstBy 는 여러건 중 가장 최근의 1건을 리턴해줌
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
}
