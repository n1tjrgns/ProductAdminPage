package com.n1tjrgns.admin.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"orderGroup","item"})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal total_price;

    private LocalDateTime createdAt;

    private String createdBy;

    //OrderDetail N : 1 Item
    @ManyToOne
    private Item item;
    //private Long itemId;

    //OrderDetail N : 1 OrderGroup
    //MappedBy의 면수명과 일치해야하는 점
    @ManyToOne
    private OrderGroup orderGroup;
    //private Long orderGroupId;


}
