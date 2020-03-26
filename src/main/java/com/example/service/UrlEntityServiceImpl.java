package com.example.service;

import com.example.module.entity.UrlEntity;
import com.example.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UrlEntityServiceImpl
 * @Description TODO
 * @Author miaoyi
 * @Date 2020-03-24 14:42
 * @Version 1.0
 **/

@Service
public class UrlEntityServiceImpl implements UrlEntityService {
    @Autowired
    private UrlRepository urlRepository;

    @Override
    public List<UrlEntity> findAllByFlag(String flag) {
        List<UrlEntity> all = urlRepository.findAllByFlag(flag);
        return all;
    }

    @Override
    public int updateFlagById(String flag,String id) {
        int i = urlRepository.updateFlag(flag,id);
        return i;
    }
}
