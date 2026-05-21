package com.example.cloudtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cloudtest.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
