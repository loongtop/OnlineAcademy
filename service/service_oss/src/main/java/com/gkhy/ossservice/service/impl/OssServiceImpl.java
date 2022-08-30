package com.gkhy.ossservice.service.impl;

import com.gkhy.ossservice.service.OssService;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service
public class OssServiceImpl implements OssService {

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    @Autowired
    private Storage storage;

    @Value("${bucketname}")
    String bucketName;
    @Value("${subdirectory}")
    String subdirectory;

    @Override
    public URL uploadFile(MultipartFile multipartFile) throws IOException {

        Credentials credentials = GoogleCredentials.fromStream(new ClassPathResource("key.json").getInputStream());
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        //Convert the file to a byte array
        final byte[] byteArray = convertToByteArray(multipartFile);

        //BlobId is a combination of bucketName + subdirectiory(optional) + fileName
        final BlobId blobId = constructBlobId(bucketName, subdirectory, multipartFile.getOriginalFilename());

        //Prepare the BlobInfo
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, byteArray);
        return getStorage().signUrl(blobInfo, 10, TimeUnit.MINUTES, Storage.SignUrlOption.withPathStyle());
    }

    /**
     * Construct Blob ID
     *
     * @param bucketName bucketName
     * @param subdirectory optional
     * @param fileName fileName
     * @return BlobId
     */
    private BlobId constructBlobId(String bucketName, @Nullable String subdirectory, String fileName) {
        String filePath = new DateTime().toString("yyyy/MM/dd") + "/" + fileName;
        return Optional.ofNullable(subdirectory)
                .map(s -> BlobId.of(bucketName, subdirectory + "/" + filePath))
                .orElse(BlobId.of(bucketName, filePath));
    }

    /**
     * Here, we convert the file to a byte array to be sent to GCS Libraries
     * @return Byte Array with all the contents of the file
     */

    @SneakyThrows
    private byte[] convertToByteArray(MultipartFile multipartFile) {
        byte[] byteArray = new byte[0];

        try {
            byteArray = multipartFile.getBytes();

        } catch (IOException e) {
            log.error("read request body error...", e);
            e.printStackTrace();
        }
        return byteArray;
    }
}
