package com.mooc.reader.service;

import com.mooc.reader.entity.Member;

public interface MemberService {
    Member createMember(String username, String password, String nickname);
    Member checkLogin(String username, String password);
}
