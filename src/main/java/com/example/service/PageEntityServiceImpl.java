package com.example.service;

import com.example.module.entity.PageEntity;
import com.example.repository.PageEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName PageEntityServiceImpl
 * @Description TODO
 * @Author miaoyi
 * @Date 2020-03-24 14:20
 * @Version 1.0
 **/

@Service
public class PageEntityServiceImpl implements PageEntityService {

    @Autowired
    private PageEntityRepository pageEntityRepository;

    @Override
    public void savePageEntity(PageEntity pageEntity){
        pageEntityRepository.saveAndFlush(pageEntity);
    }

}
