package com.gkhy.cmsservice.controller;

import com.gkhy.cmsservice.entity.CrmBanner;
import com.gkhy.cmsservice.service.CrmBannerService;
import com.gkhy.servicebase.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * front banner display
 * </p>
 *
 * @author leo
 * @since 2020-07-20
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public final class BannerFrontController {

    private final CrmBannerService bannerService;
    @Autowired
    public BannerFrontController(CrmBannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping("getAllBanner")
    public Result getAllBanner() {
        List<CrmBanner> list = bannerService.findAllOrderByIdDescLimit3();
        return Result.success().data("list",list);
    }
}

