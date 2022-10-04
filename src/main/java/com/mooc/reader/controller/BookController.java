package com.mooc.reader.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.reader.entity.Book;
import com.mooc.reader.service.BookService;
import com.mooc.reader.utils.ResponseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("/list")
    public ResponseUtils selectBook(Long categoryId, String order,
                                    @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer rows) {
        ResponseUtils result;
        try {
            IPage<Book> bookPage = bookService.selectPage(categoryId, order, page, rows);
            List<Book> records = bookPage.getRecords();
            result = new ResponseUtils();
            result.put("list", records);
            result.put("current", bookPage.getCurrent());
            result.put("total", bookPage.getTotal());
            result.put("rows", bookPage.getSize());
            result.put("pages", bookPage.getPages());
        } catch (Exception e) {
            result = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return result;
    }
}
