package com.gkhy.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.gkhy.eduservice.entity.SubjectEntity;
import com.gkhy.eduservice.entity.excel.SubjectData;
import com.gkhy.eduservice.entity.subject.MainSubject;
import com.gkhy.eduservice.entity.subject.SecondarySubject;
import com.gkhy.eduservice.listener4Excel.SubjectExcelListener;
import com.gkhy.eduservice.repository.SubjectRepository;
import com.gkhy.eduservice.service.SubjectService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * EduSubject Service Implementation Class
 * </p>
 *
 * @author leo
 * @since 2022-07-18
 */

@Service
public final class SubjectServiceImpl extends
        ServiceImpl<SubjectEntity, Long, SubjectRepository>
        implements SubjectService {

    @Autowired
    public SubjectServiceImpl(SubjectRepository iRepository) {
        super(iRepository);
    }

    @Override
    public void saveSubject(MultipartFile file, SubjectService subjectService) {
        try {
            //file input stream
            InputStream in = file.getInputStream();
            //call method to read
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<MainSubject> getAllOneTwoSubject() {
        //Create Specification object
        Specification<SubjectEntity> specification =
                (root, query, cb) -> cb.equal(root.get("parentId"), "0");

        //Create a list collection to store the final encapsulated data
        List<SubjectEntity> oneSubjectList = this.findAll(specification);

        specification = (root, query, cb) -> cb.notEqual(root.get("parentId"), "0");

        List<SubjectEntity> twoSubjectList = this.findAll(specification);

        List<MainSubject> finalSubjectList = new ArrayList<>();

        for(SubjectEntity eduSubject1: oneSubjectList) {
            MainSubject mainSubject = new MainSubject();
            mainSubject.setId(eduSubject1.getId());
            mainSubject.setTitle(eduSubject1.getTitle());

            BeanUtils.copyProperties(eduSubject1,mainSubject);
            finalSubjectList.add(mainSubject);

            List<SecondarySubject> twoFinalSubjectList = new ArrayList<>();
            for(SubjectEntity eduSubject2: twoSubjectList) {
                if(eduSubject2.getParentId().equals(eduSubject1.getId())) {

                    SecondarySubject twoSubject = new SecondarySubject();
                    BeanUtils.copyProperties(eduSubject2,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            mainSubject.setChildren(twoFinalSubjectList);
        }
        return finalSubjectList;
    }

}
