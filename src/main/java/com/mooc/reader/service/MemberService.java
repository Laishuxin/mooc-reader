package com.mooc.reader.service;

import com.mooc.reader.entity.Member;
import com.mooc.reader.entity.MemberReadState;

public interface MemberService {
    Member createMember(String username, String password, String nickname);
    Member checkLogin(String username, String password);
    Member selectById(Long memberId);

    MemberReadState selectReadState(Long memberId, Long bookId);
}
