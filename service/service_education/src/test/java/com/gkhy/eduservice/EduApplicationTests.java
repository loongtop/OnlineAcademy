package com.gkhy.eduservice;

import com.gkhy.eduservice.entity.TeacherEntity;
import com.gkhy.eduservice.service.impl.TeacherServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class EduApplicationTests {

    @Autowired
    private TeacherServiceImpl eduTeacherService;

    @Test
    void findAll() {
        // test
        List<TeacherEntity> eduTeacherList =  eduTeacherService.findAll();
        System.out.println(eduTeacherList);
    }

    @Test
    public void findOne() {
        Specification<TeacherEntity> spe = new Specification<>() {
            /**
             * return Predicate: Defined query criteria .
             * param "Root<EduTeacher>" root: Root object , The object that encapsulates the query criteria .
             * param "CriteriaQuery<?>" query: Define a basic query , Generally not used .
             * param "CriteriaBuilder" cb: Create a query condition .
             */
            @Override
            public Predicate toPredicate(Root<TeacherEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("name"), "王二");
            }
        };
        Optional<TeacherEntity> eduTeacher = eduTeacherService.findOne(spe);
        System.out.println(eduTeacher);
    }

    @Test
    public void findOneByNameAndLevel() {
        Specification<TeacherEntity> spe = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(cb.equal(root.get("name"), "王五"));
            list.add(cb.equal(root.get("level"), 1));
            int length = list.size();
            Predicate[] arr = new Predicate[length];
            return cb.and(list.toArray(arr));
        };
        Optional<TeacherEntity> eduTeacher = eduTeacherService.findOne(spe);
        System.out.println(eduTeacher);
    }

    @Test
    public void findByNameOr() {
        Specification<TeacherEntity> spe = (root, query, cb) -> cb.or(cb.equal(root.get("name"), "李四"), cb.equal(root.get("name"), "张三"));
        List<TeacherEntity> list = eduTeacherService.findAll(spe);
        for (TeacherEntity user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void findByNameLike() {
        Specification<TeacherEntity> spe = (root, query, cb) -> cb.like(root.get("name"), "李%");
        List<TeacherEntity> list = eduTeacherService.findAll(spe);
        for (TeacherEntity user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void findByTimeLike() {

        String begin = "2017-10-30T02:18:46.000";
        String end = "2020-10-30T02:18:46.000";
        LocalDateTime beginLocal = LocalDateTime.parse(begin);
        LocalDateTime endLocal = LocalDateTime.parse(end);

        //Create Specification object
        Specification<TeacherEntity> specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(cb.equal(root.get("name"), "张三"));

            list.add(cb.greaterThanOrEqualTo(root.get("gmtCreate"), beginLocal));
            list.add(cb.lessThanOrEqualTo(root.get("gmtModified"), endLocal));

            Predicate[] arr = new Predicate[list.size()];
            return cb.and(list.toArray(arr));
        };

        Pageable pageable = PageRequest.of(0, 2);

        Page<TeacherEntity> eduTeacherList = eduTeacherService.findAll(specification, pageable);

        for (TeacherEntity user : eduTeacherList) System.out.println(user);

    }

    @Test
    public void pageable() {
        Specification<TeacherEntity> spe = (root, query, cb) -> cb.between(root.get("id"), 47, 51);
        Pageable pageable = PageRequest.of(0, 5);
        Page<TeacherEntity> list = eduTeacherService.findAll(spe, pageable);
        for (TeacherEntity user : list) System.out.println(user);
    }

    @Test
    public void sort() {
        Specification<TeacherEntity> spe = (root, query, cb) -> cb.greaterThan(root.get("id"), 51);
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<TeacherEntity> list = eduTeacherService.findAll(spe, sort);
        for (TeacherEntity user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void pageableAndSort() {
        Specification<TeacherEntity> spe = (root, query, cb) -> cb.ge(root.get("id"), 51);
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 3, sort);
        Page<TeacherEntity> list = eduTeacherService.findAll(spe, pageable);
        for (TeacherEntity user : list) System.out.println(user);
    }

}