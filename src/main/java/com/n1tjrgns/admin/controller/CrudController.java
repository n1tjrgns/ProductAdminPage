package com.n1tjrgns.admin.controller;

import com.n1tjrgns.admin.ifs.CrudInterface;
import com.n1tjrgns.admin.model.network.Header;
import com.n1tjrgns.admin.repository.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

//[리팩토링] 추상 컨트롤러 클래스 만들기
//매번 컨트롤러를 만들 때 마다 해당 메소드들을 오버라이딩 시켜줘야하는게 불편해서
@Component
public abstract class CrudController<Req,Res,Entity> implements CrudInterface<Req,Res> {

    //상속받는 클래스에서만 접근 할 수 있도록
    @Autowired(required = false)
    protected BaseService<Req,Res,Entity> baseService;

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) {
        return baseService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) {
        return baseService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> request) {
        return baseService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return baseService.delete(id);
    }
}
