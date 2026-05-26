package com.example.cloudtest.service;

import java.io.IOException;
import java.time.Duration;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

@Slf4j
@Service
public class S3Service {

	private final S3Client s3Client;
	private final S3Presigner s3Presigner;
	private final String bucketName = "my-cloudtest-bucket-jihyeon";

	public S3Service() {
		this.s3Client = S3Client.builder()
			.region(Region.AP_NORTHEAST_2)
			.build();
		this.s3Presigner = S3Presigner.builder()
			.region(Region.AP_NORTHEAST_2)
			.build();
	}

	// S3에 이미지 업로드
	public String uploadImage(MultipartFile file, Long memberId) throws IOException {
		String key = "profile-images/" + memberId + "/" + file.getOriginalFilename();

		s3Client.putObject(
			PutObjectRequest.builder()
				.bucket(bucketName)
				.key(key)
				.contentType(file.getContentType())
				.build(),
			RequestBody.fromBytes(file.getBytes())
		);

		return key;
	}

	// Presigned URL 생성 (7일)
	public String generatePresignedUrl(String key) {
		GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
			.signatureDuration(Duration.ofDays(7))
			.getObjectRequest(r -> r.bucket(bucketName).key(key))
			.build();

		return s3Presigner.presignGetObject(presignRequest).url().toString();
	}
}
