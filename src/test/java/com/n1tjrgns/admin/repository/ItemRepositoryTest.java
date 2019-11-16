package com.n1tjrgns.admin.repository;

import com.n1tjrgns.admin.AdminApplicationTests;
import com.n1tjrgns.admin.model.entity.Item;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends AdminApplicationTests {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = new Item();
        item.setStatus("UNREGISTERD");
        item.setName("맥북 pro");
        item.setPrice(BigDecimal.valueOf(3000000));
        item.setTitle("맥북 프로");
        item.setContent("2019 애플 맥북 프로");
        item.setBrandName("apple");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");
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
