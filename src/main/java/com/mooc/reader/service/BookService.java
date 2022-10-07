package com.mooc.reader.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.reader.entity.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public interface BookService {
    /**
     * 分页查询图书
     *
     * @param categoryId 分类编号
     * @param order      排序方式
     * @param page       页号
     * @param rows       每页记录数
     * @return 分页对象
     */
    IPage<Book> selectPage(Long categoryId, String order, Integer page, Integer rows);

    /**
     * 根据图书编号查询图书对象
     *
     * @param bookId 图书编号
     * @return 图书对象
     */
    Book selectById(Long bookId);

    void updateScore();

//    void updateScore();
//
//    IPage<Map> selectBookMap(Integer page, Integer rows);
//
//    Book createBook(Book book);
//
//    Book updateBook(Book book);
//
//    void deleteBook(Long bookId);

}
