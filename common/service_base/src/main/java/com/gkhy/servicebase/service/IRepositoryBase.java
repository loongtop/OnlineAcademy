package com.gkhy.servicebase.service;

import com.gkhy.servicebase.service.repository.IJpaRepository;
import com.gkhy.servicebase.service.repository.IService;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRepositoryBase<T> extends
        IService<T>,
        IJpaRepository<T, Long> {
}
