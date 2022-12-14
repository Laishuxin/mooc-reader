package com.mooc.reader.controller;

import com.mooc.reader.dto.UpdateReadStateDto;
import com.mooc.reader.entity.Member;
import com.mooc.reader.entity.MemberReadState;
import com.mooc.reader.service.MemberService;
import com.mooc.reader.utils.ResponseUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Resource
    private MemberService memberService;

    @PostMapping("/register")
    public ResponseUtils register(@RequestBody Map<String, String> data,
                                  HttpServletRequest request) {
        ResponseUtils res;
        String username = data.get("username");
        String password = data.get("password");
        String nickname = data.get("nickname");
        String verifyCode = data.get("verifyCode");
        String vc = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        System.out.println("vc: " + vc);
        System.out.println("verifyCode: " + verifyCode);
        System.out.println("username: " + data);
        if (vc == null || vc.equals(verifyCode) == false) {
            res = new ResponseUtils("Invalid verify code", "验证码无效");
            return res;
        }

        try {
            Member member = memberService.createMember(username, password, nickname);
            member.setPassword(null);
            res = new ResponseUtils();
            res.put("member", member);
        } catch (Exception e) {
            res = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return res;
    }

    @PostMapping("/login")
    public ResponseUtils login(@RequestBody Map<String, String> map, HttpServletRequest request) {
        String verifyCode = map.get("verifyCode");
        String username = map.get("username");
        String password = map.get("password");
        ResponseUtils res;

        String kaptchaVerifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        if (kaptchaVerifyCode.equals(verifyCode) == false) {
            res = new ResponseUtils("invalid verify code", "验证码错误");
            return res;
        }

        try {
            Member member = memberService.checkLogin(username, password);
            res = new ResponseUtils();
            res.put("member", member);
        } catch (Exception e) {
            res = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return res;
    }

    @GetMapping("/{id}")
    public ResponseUtils selectById(@PathVariable("id") Long memberId) {
        ResponseUtils res;
        try {
            Member member = memberService.selectById(memberId);
            res = new ResponseUtils();
            res.put("member", member);
        } catch (Exception e) {
            res = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return res;
    }

    @GetMapping("/read_state")
    public ResponseUtils selectReadState(Long memberId, Long bookId) {
        try {
            MemberReadState memberReadState = memberService.selectReadState(memberId, bookId);
            return new ResponseUtils().put("readState", memberReadState);
        } catch (Exception e) {
            return new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    @PostMapping("/read_state")
    public ResponseUtils updateReadState(@RequestBody UpdateReadStateDto body) {
        try {
            MemberReadState memberReadState = memberService.updateReadState(body.getMemberId(), body.getBookId(), body.getReadState());
            return new ResponseUtils().put("readState", memberReadState);
        } catch (Exception e) {
            return new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
