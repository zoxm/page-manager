package com.example.service;


import com.example.module.entity.UrlEntity;

import java.util.List;

/**
 * @ClassName UrlEntityService
 * @Description TODO
 * @Author miaoyi
 * @Date 2020-03-24 14:42
 * @Version 1.0
 **/

public interface UrlEntityService {
    int updateFlagById(String flag,String id);

    List<UrlEntity> findAllByFlag(String flag);
}
