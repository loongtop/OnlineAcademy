package com.gkhy.ossservice.controller;

import com.gkhy.servicebase.result.Result;
import com.gkhy.ossservice.service.OssService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @SneakyThrows
    @PostMapping(value = "/upload")
    public Result uploadOssFile(MultipartFile file) {
        //Returns the path uploaded to oss
        URL url = ossService.uploadFile(file);
        return Result.success().data("url", url);
    }
}
