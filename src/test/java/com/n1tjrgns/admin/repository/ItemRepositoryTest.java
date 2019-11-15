package com.n1tjrgns.admin.repository;

import com.n1tjrgns.admin.AdminApplicationTests;
import com.n1tjrgns.admin.model.entity.Item;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ItemRepositoryTest extends AdminApplicationTests {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = new Item();
        item.setName("맥북");
        item.setPrice(3000000);
        item.setTitle("맥북ㅍㅍ");
        item.setContent("애플 맥북 프로");
        item.setPartnerId(1L);

        Item newItem = itemRepository.save(item);

        //create가 됐다면 newItem은 not null 이므로 아래 함수 사용
        Assert.assertNotNull(newItem);
    }

    @Test
    public void read(){
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);

        Assert.assertTrue(item.isPresent());

        item.ifPresent(i -> {
            System.out.println(i);
        });
    }
}
