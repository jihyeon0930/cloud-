package com.example.cloudtest.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	@PostMapping("/{id}/profile-image")
	public ResponseEntity<String> uploadProfileImage(
		@PathVariable Long id,
		@RequestParam("file") MultipartFile file) throws IOException {
		String url = memberService.uploadProfileImage(id, file);
		return ResponseEntity.ok(url);
	}

	@GetMapping("/{id}/profile-image")
	public ResponseEntity<String> getProfileImage(@PathVariable Long id) {
		String presignedUrl = memberService.getProfileImageUrl(id);
		return ResponseEntity.ok(presignedUrl);
	}
}