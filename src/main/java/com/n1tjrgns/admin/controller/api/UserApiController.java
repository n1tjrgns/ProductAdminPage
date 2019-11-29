package com.n1tjrgns.admin.controller.api;

//사용자 api 비즈니스 로직 컨트롤러

import com.n1tjrgns.admin.controller.CrudController;
import com.n1tjrgns.admin.model.entity.User;
import com.n1tjrgns.admin.model.network.request.UserApiRequest;
import com.n1tjrgns.admin.model.network.response.UserApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // 로깅
@RestController
@RequestMapping("/api/user")
//public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {

    /*@Autowired
    private UserApiLogicService userApiLogicService;

    @PostConstruct
    public void init(){
        this.baseService = userApiLogicService;
    }*/

    /*@Override
    @PostMapping("") //api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {

        log.info("{}",request); //로깅
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") //api/user/{id}
    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {

        log.info("read id : {}", id);
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("") //api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") //api/user/{id}
    public Header delete(@PathVariable Long id) {
        log.info("delete id : {}",id);
        return userApiLogicService.delete(id);
    }*/
}
