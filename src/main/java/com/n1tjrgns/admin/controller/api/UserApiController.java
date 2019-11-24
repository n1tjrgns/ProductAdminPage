package com.n1tjrgns.admin.controller.api;

import com.n1tjrgns.admin.ifs.CrudInterface;
import com.n1tjrgns.admin.model.network.Header;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface {


    @Override
    public Header create() {
        return null;
    }

    @Override
    public Header read(Long id) {
        return null;
    }

    @Override
    public Header update() {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
