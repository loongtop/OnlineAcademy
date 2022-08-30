package com.gkhy.vodservice.service.impl;


import com.gkhy.servicebase.exception.AcademyException;
import com.gkhy.vodservice.service.VodService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVideoAly(MultipartFile file) {
        try {
            return null;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeMoreAlyVideo(List<Long> videoIdList) {
        try {

        }catch(Exception e) {
            e.printStackTrace();
            throw new AcademyException(20001,"fail to delete the video!");
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("11");
        list.add("22");
        list.add("33");
        // 11,22,33
        String join = StringUtils.join(list.toArray(), ",");
        System.out.println(join);
    }
}
