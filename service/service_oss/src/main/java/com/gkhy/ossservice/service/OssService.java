package com.gkhy.ossservice.service;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URL;

public interface OssService {

    URL uploadFile(MultipartFile file) throws IOException;
}
