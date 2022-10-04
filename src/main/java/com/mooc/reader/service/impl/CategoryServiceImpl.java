package com.mooc.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mooc.reader.entity.Category;
import com.mooc.reader.mapper.CategoryMapper;
import com.mooc.reader.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    public List<Category> selectAll() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByAsc("category_id");
        return categoryMapper.selectList(queryWrapper);
    }
}
