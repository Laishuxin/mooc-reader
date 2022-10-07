package com.mooc.reader.controller;

import com.mooc.reader.entity.MemberReadState;
import com.mooc.reader.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MemberControllerTest {

    @Resource
    private MemberService memberService;

    @Test
    public void selectReadState() {
        MemberReadState memberReadState = memberService.selectReadState(331l, 5l);
        System.out.println(memberReadState);
    }
}