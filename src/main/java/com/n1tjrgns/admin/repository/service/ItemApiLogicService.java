package com.n1tjrgns.admin.repository.service;

import com.n1tjrgns.admin.model.entity.Item;
import com.n1tjrgns.admin.model.network.Header;
import com.n1tjrgns.admin.model.network.request.ItemApiRequest;
import com.n1tjrgns.admin.model.network.response.ItemApiResponse;
import com.n1tjrgns.admin.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
//[리팩토링] Service 추상화하기 itemRepository -> baseRepository
//public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {
    @Autowired
    private PartnerRepository partnerRepository;

    /*@Autowired
    private ItemRepository itemRepository;*/

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {

        ItemApiRequest body = request.getData();

        Item item = Item.builder()
                .status(body.getStatus())
                .name(body.getName())
                .title(body.getTitle())
                .content(body.getContent())
                .price(body.getPrice())
                .brandName(body.getBrandName())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getOne(body.getPartnerId()))
                .build();

        //Item newItem = itemRepository.save(item);
        Item newItem = baseRepository.save(item);

        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {

        //return itemRepository.findById(id)
        return baseRepository.findById(id)
                .map(item -> response(item))
                .orElseGet(()->{
                   return Header.ERROR("데이터 없음");
                });
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {

        ItemApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(entityItem -> {
                    entityItem
                            .setStatus(body.getStatus())
                            .setName(body.getName())
                            .setTitle(body.getTitle())
                            .setContent(body.getContent())
                            .setPrice(body.getPrice())
                            .setBrandName(body.getBrandName())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt());

                    return entityItem;
                })
                .map(newEntityItem -> baseRepository.save(newEntityItem))
                .map(item -> response(item))
                .orElseGet(()-> Header.ERROR("데이터없음"));
    }

    @Override
    public Header delete(Long id) {

        return baseRepository.findById(id)
                .map(item -> {
                    //delete는 void를 리턴하기 때문에 중괄호로 한 번 더 묶어서 리턴
                    baseRepository.delete(item);
                        return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    //공통 응답 메소드
    public Header<ItemApiResponse> response(Item item){
        ItemApiResponse body = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();

        return Header.OK(body);
    }

    @Override
    public Header<List<ItemApiResponse>> search(Pageable pageable) {
        return null;
    }
}
