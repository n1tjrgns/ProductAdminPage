package com.n1tjrgns.admin.repository;

import com.n1tjrgns.admin.AdminApplicationTests;
import com.n1tjrgns.admin.model.entity.Category;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends AdminApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void categoryCreate(){
        String type = "computer";
        String title = "맥북";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);

        //insert 되면 반드시 Not null이 아니니깐
        Assert.assertNotNull(newCategory);

        //생성한 카테고리의 타입과 집어넣은 타입은 같아야함
        Assert.assertEquals(newCategory.getType(), type);

        Assert.assertEquals(newCategory.getTitle(),title);
    }


    @Test
    public void categoryRead(){

        //Optional<Category> optionalCategory = categoryRepository.findById(1L);
        Optional<Category> optionalCategory = categoryRepository.findByType("computer");

        optionalCategory.ifPresent(c ->{

            Assert.assertEquals(c.getType(), "computer");
            System.out.println(c);
        });

    }
}
