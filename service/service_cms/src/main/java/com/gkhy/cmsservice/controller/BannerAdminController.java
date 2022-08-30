package com.gkhy.cmsservice.controller;

import com.gkhy.cmsservice.entity.CrmBanner;
import com.gkhy.cmsservice.service.CrmBannerService;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.utils.ItemFound;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * <p>
 * Background banner management interface
 * </p>
 *
 * @author leo
 * @since 2020-07-20
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public final class BannerAdminController {

    private final CrmBannerService bannerService;
    @Autowired
    public BannerAdminController(CrmBannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping("pageBanner/{page}/{limit}")
    public Result pageBanner(@PathVariable int page, @PathVariable int limit) {
        Page<CrmBanner> eduTeacherList = bannerService.findAll(page, limit);
        long total = eduTeacherList.getNumberOfElements ();
        return Result.success().data("total",total).data("lists",eduTeacherList.getContent());
    }

    @PostMapping("add")
    public Result addBanner(@RequestBody CrmBanner crmBanner) {
        CrmBanner banner = bannerService.save(crmBanner);
        return Result.success().data("item", banner);
    }

    @ApiOperation(value = "get Banner")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Optional<CrmBanner> crmBanner = bannerService.findById(id);
        if (crmBanner.isEmpty()) return ItemFound.fail();

        return Result.success().data("item", crmBanner);
    }

    @ApiOperation(value = "edit Banner")
    @PutMapping("update")
    public Result update(@RequestBody CrmBanner banner) {

        CrmBanner entity = bannerService.save(banner);
        return Result.success().data("item", entity);
    }

    @ApiOperation(value = "delete Banner")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        bannerService.removeById(id);
        return Result.success();
    }
}

