package com.gkhy.cmsservice.service;

import com.gkhy.cmsservice.entity.CrmBanner;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CrmBannerService {
    Page<CrmBanner> findAll(int page, int limit);

    CrmBanner save(CrmBanner crmBanner);

    Optional<CrmBanner> findById(Long id);

    void removeById(Long id);

    List<CrmBanner> findAllOrderByIdDescLimit3();
}
