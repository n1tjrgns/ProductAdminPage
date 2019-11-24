package com.n1tjrgns.admin.repository.service;

import com.n1tjrgns.admin.ifs.CrudInterface;
import com.n1tjrgns.admin.model.entity.User;
import com.n1tjrgns.admin.model.network.Header;
import com.n1tjrgns.admin.model.network.request.UserApiRequest;
import com.n1tjrgns.admin.model.network.response.UserApiResponse;
import com.n1tjrgns.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    //1. request data
    //2. user 생성
    //3. 생성된 데이터 -> UserApiResponse return



    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        //1. request data
        UserApiRequest userApiRequest = request.getData();

        //2. user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        //3. 생성된 데이터 -> userApiResponse return
        //응답에 대한 리턴은 다른 부분에도 사용될 수 있기 때문에 별도 메소드 작성.

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        //id -> repository getOne, getById
        Optional<User> optional = userRepository.findById(id);

        //user -> userApiResponse return
        return optional
                .map(user -> response(user))
                .orElseGet( //데이터가 없을 경우 예외처리
                        ()->Header.ERROR("데이터없음")
                );
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<UserApiResponse> response(User user){
        // user -> userApiResponse return

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unRegisterdAt(user.getUnregisteredAt())
                .build();

        //Header + data return
        return Header.OK(userApiResponse);
    }
}
