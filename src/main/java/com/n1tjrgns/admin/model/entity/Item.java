package com.n1tjrgns.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//아이템
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String title;

    private Integer price;

    private String content;

    private Long partnerId;


    // FETCH 옵션
    // LAZY = 지연로딩, EAGER = 즉시로딩
    // 1:N
    // LAZY = SELECT * from item where id = ?

    // EAGER = 1:1 이나 1건만 존재할 때 사용
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;
}
