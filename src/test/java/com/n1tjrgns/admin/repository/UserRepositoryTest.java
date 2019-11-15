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
        System.out.println(userRepository);
        User user = new User();
        user.setAccount("TestUser01");
        user.setEmail("TestUser01@gmail.com");
        user.setPhoneNumber("010-1111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");
        System.out.println(user);

        User newUser = userRepository.save(user);
        System.out.println("newUser : "+ newUser );
    }

    @Test
    public void read(){
        //제네릭 타입으로 받게되어있다.
       //Optional<User> user = userRepository.findById(1L);

        //쿼리 메소드 사용
        Optional<User> user = userRepository.findByAccount("TestUser01");
       //Optional 객체가 감싸고 있는 값이 존재할 경우에만 실행 로직을 함수형 인자로 넘김
       //user.ifPresent(System.out::println);
        user.ifPresent(selectUser->{
            System.out.println("user : " + selectUser);
            System.out.println("user : " + selectUser.getEmail());
        });


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
