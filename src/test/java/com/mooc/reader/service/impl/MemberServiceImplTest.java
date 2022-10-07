package com.mooc.reader.service.impl;

import com.mooc.reader.entity.Member;
import com.mooc.reader.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MemberServiceImplTest {
    @Resource
    private MemberService memberService;

    @Test
    public void createMember() {
        Member member = memberService.createMember("username1", "password1", "nickname1");
        System.out.println(member);
    }
}