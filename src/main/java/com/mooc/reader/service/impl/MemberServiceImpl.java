package com.mooc.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mooc.reader.entity.Member;
import com.mooc.reader.entity.MemberReadState;
import com.mooc.reader.mapper.MemberMapper;
import com.mooc.reader.mapper.MemberReadStateMapper;
import com.mooc.reader.service.MemberService;
import com.mooc.reader.service.exception.MemberException;
import com.mooc.reader.utils.Md5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class MemberServiceImpl implements MemberService {
    @Resource
    private MemberMapper memberMapper;
    @Resource
    MemberReadStateMapper memberReadStateMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Member createMember(String username, String password, String nickname) {
        QueryWrapper<Member> memberWrapper = new QueryWrapper();
        memberWrapper.eq("username", username);
        List<Member> members = memberMapper.selectList(memberWrapper);
        if (members.size() > 0) {
            throw new MemberException("用户已存在");
        }
        Member member = new Member();
        member.setUsername(username);
        member.setCreateTime(new Date());
        member.setNickname(nickname);
        Integer salt = new Random().nextInt(1000) + 1000;
        member.setSalt(salt);
        member.setPassword(Md5Utils.md5Digest(password, salt));

        memberMapper.insert(member);
        return member;
    }

    @Override
    public Member checkLogin(String username, String password) {
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<Member>();
        memberQueryWrapper.eq("username", username);
        Member member = memberMapper.selectOne(memberQueryWrapper);
        if (member == null) {
            throw new MemberException("未注册");
        }
        String encryptedPassword = Md5Utils.md5Digest(password, member.getSalt());
        if (encryptedPassword.equals(member.getPassword()) == false) {
            throw new MemberException("密码错误");
        }
        return member;
    }

    @Override
    public Member selectById(Long memberId) {
        Member member = memberMapper.selectById(memberId);
        return member;
    }

    @Override
    public MemberReadState selectReadState(Long memberId, Long bookId) {
        QueryWrapper<MemberReadState> memberReadStateQueryWrapper = new QueryWrapper<MemberReadState>();
        memberReadStateQueryWrapper.eq("book_id", bookId);
        memberReadStateQueryWrapper.eq("member_id", memberId);
        return memberReadStateMapper.selectOne(memberReadStateQueryWrapper);
    }

    @Override
    public MemberReadState updateReadState(Long memberId, Long bookId, Integer readState) {
        MemberReadState memberReadState = selectReadState(memberId, bookId);
        if (memberReadState == null) {
            memberReadState = new MemberReadState();
            memberReadState.setBookId(bookId);
            memberReadState.setMemberId(memberId);
            memberReadState.setCreateTime(new Date());
            memberReadStateMapper.insert(memberReadState);
        } else {
            memberReadState.setReadState(readState);
            memberReadState.setCreateTime(new Date());
            memberReadStateMapper.updateById(memberReadState);
        }

        return memberReadState;
    }
}
