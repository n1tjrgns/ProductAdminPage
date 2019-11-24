package com.n1tjrgns.admin.ifs;

import com.n1tjrgns.admin.model.network.Header;

//userapicontroller에서 crud에 대해 빼먹을 수 있으니 강제로 인터페이스에 정의하도록
public interface CrudInterface<Req,Res> {

    Header<Res> create(Header<Req> request); //todo request object 추가

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);
}
