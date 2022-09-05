package com.gkhy.servicebase.service;

import com.gkhy.servicebase.service.repository.IJpaRepository;
import com.gkhy.servicebase.service.repository.IService;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface IRepositoryBase<T, E extends Number> extends
        IService<T, E>,
        IJpaRepository<T, E> {
}
