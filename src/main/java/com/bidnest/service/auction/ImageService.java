package com.bidnest.service.auction;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import com.bidnest.model.auction.Image;
import com.bidnest.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private static final String BUCKET_NAME = "bidnest.com";
    private static final List<String> SUPPORTED_CONTENT_TYPES = Arrays.asList(
            "image/jpeg",
            "image/png",
            "image/gif");

    private final S3Client s3Client;
    private final ImageRepository imageRepository;

    public Image uploadImageFile(Long auctionId, MultipartFile imageFile) {
        checkFile(imageFile);
        String key = generateKey(auctionId, imageFile);
        uploadToS3(key, imageFile);

        return new Image(key);
    }

    private void checkFile(MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty imageFile [" + imageFile.getSize() + "]");
        }

        if (!SUPPORTED_CONTENT_TYPES.contains(imageFile.getContentType())) {
            throw new IllegalStateException("File must be an image [" + imageFile.getContentType() + "]");
        }
    }

    private String generateKey(Long auctionId, MultipartFile imageFile) {
        return String.format("auction-photo/%s/%s-%s", auctionId, imageFile.getOriginalFilename(), UUID.randomUUID());
    }

    private void uploadToS3(String key, MultipartFile file) {
        PutObjectRequest request = generateRequest(key);
        RequestBody requestBody = generateRequestBody(file);
        s3Client.putObject(request, requestBody);
    }

    private PutObjectRequest generateRequest(String key) {
        return PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .build();
    }

    private RequestBody generateRequestBody(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            return RequestBody.fromInputStream(inputStream, file.getSize());
        } catch (IOException e) {
            // TODO
            throw new RuntimeException(e);
        }
    }
}

//    public byte[] downloadUserProfileImage(UUID userProfileId) {
//        UserProfile user = getUserProfileOrThrow(userProfileId);
//
//        String path = String.format("%s/%s",
//                BucketName.PROFILE_IMAGE.getBucketName(),
//                user.getUserProfileId());
//
//        return user.getUserProfileImageLink()
//                .map(key -> amazonFileStore.download(path, key))
//                .orElse(new byte[0]);
//    }
//
//    private Map<String, String> extractMetadata(MultipartFile file) {
//        Map<String, String> metadata = new HashMap<>();
//        metadata.put("Content-Type", file.getContentType());
//        metadata.put("Content-Length", String.valueOf(file.getSize()));
//        return metadata;
//    }
//
//    private UserProfile getUserProfileOrThrow(UUID userProfileId) {
//        return userProfileDataAccessService
//                .getUserProfiles()
//                .stream()
//                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
//                .findFirst()
//                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
//    }