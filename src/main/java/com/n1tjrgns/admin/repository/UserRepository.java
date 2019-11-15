package com.n1tjrgns.admin.repository;

import com.n1tjrgns.admin.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //select * from user where account = ~
    //매개변수명은 실제 칼럼명과 달라도 메소드명만 맞으면 상관없음, 암거나 가능
    Optional<User> findByAccount (String account);

    Optional<User> findByAccountAndEmail(String account, String email);
}
