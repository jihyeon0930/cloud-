package com.example.cloudtest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cloudtest.dto.MemberRequestDto;
import com.example.cloudtest.entity.Member;
import com.example.cloudtest.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping
	public ResponseEntity<Member> createMember(@RequestBody MemberRequestDto dto) {
		Member member = memberService.saveMember(dto);
		return ResponseEntity.ok(member);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Member> getMember(@PathVariable Long id) {
		Member member = memberService.getMember(id);
		return ResponseEntity.ok(member);
	}
}