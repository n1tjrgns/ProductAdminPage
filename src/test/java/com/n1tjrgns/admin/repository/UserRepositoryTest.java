package com.n1tjrgns.admin.repository;


import com.n1tjrgns.admin.AdminApplicationTests;
import com.n1tjrgns.admin.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends AdminApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        String account = "Test01";
        String password = "asdf";
        String status = "REGISTERD";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-0000-0000";
        LocalDateTime registerAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registerAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);


    }

    @Test
    public void read(){

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-0000-0000");
        Assert.assertNotNull(user);
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectUser->{
           selectUser.setAccount("pppppp");
           selectUser.setUpdatedAt(LocalDateTime.now());
           selectUser.setUpdatedBy("update method()");
        });
    }


    //이런식으로 api에서 delete 요청이 들어올 경우 해당 id 값을 받아 삭제작업을 진행 할 것이다.
    /*@DeleteMapping("/api/user")
    public void delete(@RequestParam Long id){

    }*/
    @Test
    @Transactional
    public void delete(){
        //마찬가지로 select를 먼저하고
        Optional<User> user = userRepository.findById(3L);

        //데이터가 조회가 되어야 삭제를 할 수 있기 때문에 반드시 true여야 한다.
        Assert.assertTrue(user.isPresent());

        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });

        //삭제가 제대로 이뤄졌는지 확인
        Optional<User> deleteUser = userRepository.findById(3L);

        //데이터가 삭제됐으면 isPresent는 false가 나와야 한다.
        Assert.assertFalse(deleteUser.isPresent());

        /*if(deleteUser.isPresent()){
            System.out.println("데이터 존재 : "+deleteUser.get());
        }else{
            System.out.println("데이터 없음");
        }*/
    }
}
