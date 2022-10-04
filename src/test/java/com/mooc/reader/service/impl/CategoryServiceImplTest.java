package com.mooc.reader.service.impl;

import com.mooc.reader.entity.Category;
import com.mooc.reader.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CategoryServiceImplTest {
    @Resource
    private CategoryService categoryService;

    @Test
    public void selectAll() {
        List<Category> categories = categoryService.selectAll();
        for (Category category : categories) {
            System.out.println(category);
        }
    }
}