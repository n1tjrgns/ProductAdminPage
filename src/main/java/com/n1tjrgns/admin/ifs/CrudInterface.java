package com.n1tjrgns.admin.ifs;

import com.n1tjrgns.admin.model.network.Header;

//userapicontroller에서 crud에 대해 빼먹을 수 있으니 강제로 인터페이스에 정의하도록
public interface CrudInterface {

    Header create(); //todo request object 추가

    Header read(Long id);

    Header update();

    Header delete(Long id);
}
