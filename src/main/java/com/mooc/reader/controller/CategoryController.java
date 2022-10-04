package com.mooc.reader.controller;

import com.mooc.reader.entity.Category;
import com.mooc.reader.service.CategoryService;
import com.mooc.reader.utils.ResponseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResponseUtils getList() {
        ResponseUtils result;
        try {
            List<Category> categories = categoryService.selectAll();
            result = new ResponseUtils();
            result.put("list", categories);
        } catch (Exception e) {
            result = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return result;
    }
}
