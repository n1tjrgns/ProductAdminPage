package com.n1tjrgns.admin.controller.api;

import com.n1tjrgns.admin.controller.CrudController;
import com.n1tjrgns.admin.model.entity.Item;
import com.n1tjrgns.admin.model.network.request.ItemApiRequest;
import com.n1tjrgns.admin.model.network.response.ItemApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
//[리팩토링] crud 추상 컨트롤러 상속
//public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

   /* @Autowired
    private ItemApiLogicService itemApiLogicService;

    //CrudController에 Autowired된 itemApiLogicService의 의존성 주입을 적용
    @PostConstruct
    public void init(){
        this.baseService = itemApiLogicService;
    }*/


    /*@Override
    @PostMapping("")
    public Header<ItemApiResponse> create(@RequestBody  Header<ItemApiRequest> request) {
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        return itemApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        return itemApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return itemApiLogicService.delete(id);
    }*/
}
