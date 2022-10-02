package com.mooc.reader.service;

import com.mooc.reader.mapper.TestMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TestService {
    @Resource
    private TestMapper testMapper;

    @Transactional(rollbackFor = NumberFormatException.class)
    public void batchImport() {
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                throw new RuntimeException("认为异常");
            }
            testMapper.insertSample();
        }
    }
}
