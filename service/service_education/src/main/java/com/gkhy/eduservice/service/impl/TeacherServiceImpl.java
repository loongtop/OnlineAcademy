package com.gkhy.eduservice.service.impl;

import com.gkhy.eduservice.entity.TeacherEntity;
import com.gkhy.eduservice.entity.vo.TeacherVo;
import com.gkhy.eduservice.repository.TeacherRepository;
import com.gkhy.eduservice.service.TeacherService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * EduTeacher Service Implementation Class
 * </p>
 *
 * @author leo
 * @since 2022-07-11
 */

@Service
public class TeacherServiceImpl
        extends ServiceImpl<TeacherEntity, Long, TeacherRepository>
        implements TeacherService {

    @Autowired
    public TeacherServiceImpl(TeacherRepository eduTeacherRepository) {
        super(eduTeacherRepository);
    }

    @Override
    public Page<TeacherEntity> findAll(TeacherVo teacherQuery, int current, int limit) {

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime beginLocal = LocalDateTime.parse(begin,df);
        LocalDateTime endLocal = LocalDateTime.parse(end,df);

        boolean nameStatus = Objects.nonNull(name) && StringUtils.hasLength(name);
        boolean levelStatus = Objects.nonNull(level);
        boolean beginStatus = StringUtils.hasLength(begin);
        boolean endStatus = StringUtils.hasLength(end);

        if (BooleanUtils.and(new boolean[]{nameStatus, levelStatus, beginStatus, endStatus})) {
            //TODO
            System.out.println("BooleanUtils.and");
        }

        //Create Specification object
        Specification<TeacherEntity> specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();

            list.add(cb.like(root.get("name"), "%"+ name +"%"));
            list.add(cb.equal(root.get("level"), level));
            list.add(cb.greaterThanOrEqualTo(root.get("gmtCreate"), beginLocal));
            list.add(cb.lessThanOrEqualTo(root.get("gmtModified"), endLocal));

            Predicate[] arr = new Predicate[list.size()];
            return cb.and(list.toArray(arr));
        };

        Pageable pageable = PageRequest.of(current-1, limit);

        return this.findAll(specification, pageable);
    }

    @Override
    @Cacheable(key = "'selectIndexList'",value = "teacher")
    public List<TeacherEntity> findAllOrderByIdDescLimit2() {
        return this.findAllOrderByLimit(Sort.Direction.DESC, "id", 2);
    }
}
