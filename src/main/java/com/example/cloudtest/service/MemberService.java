package com.example.cloudtest.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.cloudtest.dto.MemberRequestDto;
import com.example.cloudtest.entity.Member;
import com.example.cloudtest.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final S3Service s3Service;

	public Member saveMember(MemberRequestDto dto) {
		log.info("[API - LOG] POST /api/members 요청 - name: {}, age: {}, mbti: {}"
			, dto.getName(), dto.getAge(), dto.getMbti());

		Member member = Member.builder()
			.name(dto.getName())
			.age(dto.getAge())
			.mbti(dto.getMbti())
			.build();
		return memberRepository.save(member);
	}

	public Member getMember(Long id) {
		log.info("[API - LOG GET /api/members/{} 요청", id);

		return memberRepository.findById(id)
			.orElseThrow(() -> {
				log.error("[API - LOG] Member not found - id: {}", id);
				return new RuntimeException("Member not found: "+ id);
			});
	}

	public String uploadProfileImage(Long id, MultipartFile file) throws IOException {
		log.info("[API - LOG] POST /api/members/{}/profile-image 요청", id);
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Member not found: " + id));

		String key = s3Service.uploadImage(file, id);
		member.updateProfileImageUrl(key);
		memberRepository.save(member);
		return key;
	}

	public String getProfileImageUrl(Long id) {
		log.info("[API - LOG] GET /api/members/{}/profile-image 요청", id);
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Member not found: " + id));

		return s3Service.generatePresignedUrl(member.getProfileImageUrl());
	}

}
