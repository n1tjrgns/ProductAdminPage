package com.n1tjrgns.admin.repository.service;

import com.n1tjrgns.admin.model.entity.OrderGroup;
import com.n1tjrgns.admin.model.entity.User;
import com.n1tjrgns.admin.model.enumclass.UserStatus;
import com.n1tjrgns.admin.model.network.Header;
import com.n1tjrgns.admin.model.network.request.UserApiRequest;
import com.n1tjrgns.admin.model.network.response.ItemApiResponse;
import com.n1tjrgns.admin.model.network.response.OrderGroupApiResponse;
import com.n1tjrgns.admin.model.network.response.UserApiResponse;
import com.n1tjrgns.admin.model.network.response.UserOrderInfoApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User>{

    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    @Autowired
    private ItemApiLogicService itemApiLogicService;
    /*@Autowired
    private baseRepository baseRepository;*/

    //1. request data
    //2. user 생성
    //3. 생성된 데이터 -> UserApiResponse return

    //@GetMapping("") //아이디를 기준으로 ASC 정렬하고 페이징은 10페이지 까지한다.
    @Override
    public Header<List<UserApiResponse>> search(@PageableDefault(sort="id", direction = Sort.Direction.ASC, size = 15) Pageable pageable){
        log.info("{}",pageable);

        Page<User> users = baseRepository.findAll(pageable);

        List<UserApiResponse> userApiResponseList = users.stream()
                .map(user -> response(user))
                .collect(Collectors.toList());

        //List<UserApiResponse

        //Header<List<UserApiResponse>>
        return Header.OK(userApiResponseList);
    }

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        //1. request data
        UserApiRequest userApiRequest = request.getData();

        //2. user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED) //ENUM 으로 Status 관리
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = baseRepository.save(user);

        //3. 생성된 데이터 -> userApiResponse return
        //응답에 대한 리턴은 다른 부분에도 사용될 수 있기 때문에 별도 메소드 작성.

        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        //id -> repository getOne, getById
        Optional<User> optional = baseRepository.findById(id);

        //user -> userApiResponse return
        return optional
                .map(user -> response(user))
                .map(userApiResponse -> Header.OK(userApiResponse))
                .orElseGet( //데이터가 없을 경우 예외처리
                        ()->Header.ERROR("데이터없음")
                );
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        //1. data
        UserApiRequest userApiRequest = request.getData();

        //2. id-> user 데이터 찾기
        Optional<User> optional = baseRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
            //3. update
            //체인 패턴 사용
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());

            return user;
            //4. userApiResponse
        })
                .map(user -> baseRepository.save(user)) //update
                .map(updateUser -> response(updateUser))    //userApiResponse
                .map(userApiResponse -> Header.OK(userApiResponse))
                .orElseGet(()->Header.ERROR("데이터없음"));
    }

    @Override
    public Header delete(Long id) {
        // id -> repository -> user
        Optional<User> optional = baseRepository.findById(id);

        //repository -> delete
        optional.map(user ->{
            baseRepository.delete(user);
            return Header.OK();
        })
                .orElseGet(()->Header.ERROR("데이터 없음"));
        //response return
        return null;
    }

    private UserApiResponse response(User user){
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
        //Header.OK(userApiResponse);
        return userApiResponse;
    }

    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {

        //user
        User user = baseRepository.getOne(id);
        UserApiResponse userApiResponse = response(user);


        //orderGroup
        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
                .map(orderGroup -> {

                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup).getData();
                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(detail -> detail.getItem())
                            .map(item -> itemApiLogicService.response(item).getData())
                            .collect(Collectors.toList());

                    orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                    return orderGroupApiResponse;
                })
                .collect(Collectors.toList());

        userApiResponse.setOrderGroupApiResponsesList(orderGroupApiResponseList);
        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build();

        return Header.OK(userOrderInfoApiResponse);
    }
}
