package com.web.c101.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDao extends JpaRepository<Long, Member> {
}
