package com.n1tjrgns.admin.controller.api;

import com.n1tjrgns.admin.controller.CrudController;
import com.n1tjrgns.admin.model.network.request.OrderGroupApiRequest;
import com.n1tjrgns.admin.model.network.response.OrderGroupApiResponse;
import com.n1tjrgns.admin.repository.service.OrderGroupApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/orderGroup")
//public class OrderGroupApiController implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse> {

    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    //CrudController에 Autowired된 itemApiLogicService의 의존성 주입을 적용
    @PostConstruct
    public void init(){
        this.baseService = orderGroupApiLogicService;
    }

    /*@Override
    @PostMapping("")
    public Header<OrderGroupApiResponse> create(@RequestBody  Header<OrderGroupApiRequest> request) {
        return orderGroupApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderGroupApiResponse> read(@PathVariable Long id) {
        return orderGroupApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {
        return orderGroupApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return orderGroupApiLogicService.delete(id);
    }*/
}
