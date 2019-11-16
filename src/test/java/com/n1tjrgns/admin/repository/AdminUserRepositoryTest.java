package com.n1tjrgns.admin.repository;

import com.n1tjrgns.admin.AdminApplicationTests;
import com.n1tjrgns.admin.model.entity.AdminUser;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class AdminUserRepositoryTest extends AdminApplicationTests {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void adminCreate(){

        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("AdminUser01");
        adminUser.setPassword("AdminUser01");
        adminUser.setStatus("REGISTERD");
        adminUser.setRole("PARTNER");
        adminUser.setCreatedAt(LocalDateTime.now());
        adminUser.setCreatedBy("AdminServer");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assert.assertNotNull(newAdminUser);
    }
}
