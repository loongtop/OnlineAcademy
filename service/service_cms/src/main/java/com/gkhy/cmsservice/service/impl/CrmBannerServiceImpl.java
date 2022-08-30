package com.gkhy.cmsservice.service.impl;

import com.gkhy.cmsservice.entity.CrmBanner;
import com.gkhy.cmsservice.repository.CrmBannerRepository;
import com.gkhy.cmsservice.service.CrmBannerService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBanner, Long, CrmBannerRepository>
        implements CrmBannerService {

    @Autowired
    public CrmBannerServiceImpl(CrmBannerRepository iRepository) {
        super(iRepository);
    }

    @Cacheable(key = "'selectIndexList'",value = "banner")
    public List<CrmBanner> findAllOrderByIdDescLimit3() {
        return this.findAllOrderByLimit(Sort.Direction.DESC, "id", 3);
    }

}
