package com.n1tjrgns.admin.repository;

import com.n1tjrgns.admin.AdminApplicationTests;
import com.n1tjrgns.admin.model.entity.Partner;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class PartnerRepositoryTest extends AdminApplicationTests {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void partnerCreate(){
        String name = "Partner01";
        String status = "REGISTERD";
        String address = "서울시 강남구";
        String callCenter = "070-1111-1111";
        String partnerNumber = "010-0000-0000";
        String businessNumber = "1234567890123";
        String ceoName = "홍길동";
        LocalDateTime registerAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Long categoryId = 1L;

        Partner partner = new Partner();
        partner.setName(name);
        partner.setStatus(status);
        partner.setAddress(address);
        partner.setCallCenter(callCenter);
        partner.setPartnerNumber(partnerNumber);
        partner.setBusinessNumber(businessNumber);
        partner.setCeoName(ceoName);
        partner.setRegisteredAt(registerAt);
        partner.setCreatedAt(createdAt);
        partner.setCreatedBy(createdBy);
        partner.setCategoryId(categoryId);

        Partner newPartner = partnerRepository.save(partner);

        Assert.assertNotNull(newPartner);
        Assert.assertEquals(newPartner.getName(),name);

    }

    @Test
    public void partnerRead(){

    }
}
