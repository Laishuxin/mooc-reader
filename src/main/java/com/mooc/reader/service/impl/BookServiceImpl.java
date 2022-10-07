package com.mooc.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.reader.entity.Book;
import com.mooc.reader.mapper.BookMapper;
import com.mooc.reader.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    public IPage<Book> selectPage(Long categoryId, String order, Integer page, Integer rows) {
//        if (page == null) {
//            page = 1;
//        }
//        if (rows == null) {
//            rows = 10;
//        }
        IPage<Book> p = new Page(page, rows);
        QueryWrapper<Book> wrapper = new QueryWrapper();
        if (categoryId != null && categoryId != -1) {
            wrapper.eq("category_id", categoryId);
        }

        if (order != null) {
            if (order.equals("quantity")) {
                wrapper.orderByDesc("evaluation_quantity");
            } else if (order.equals("score")) {
                wrapper.orderByDesc("evaluation_score");
            }
        } else {
            wrapper.orderByDesc("evaluation_quantity");
        }
        p = bookMapper.selectPage(p, wrapper);
        return p;
    }

    @Override
    public Book selectById(Long bookId) {
        return bookMapper.selectById(bookId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateScore() {
        bookMapper.updateScore();
    }

}
