package com.example.repository;

import com.example.module.entity.UrlEntity;
import com.example.repository.base.BaseRepository;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UrlRepository
 * @Description TODO
 * @Author miaoyi
 * @Date 2020-03-21 13:07
 * @Version 1.0
 **/

public interface UrlRepository extends BaseRepository<UrlEntity,Integer>, JpaSpecificationExecutor<UrlEntity> {



    @Transactional
    @Modifying
    @Query("UPDATE UrlEntity urlentity SET urlentity.flag = ?1 WHERE urlentity.id= ?2 ")
    int updateFlag(String flag,String id);


    List<UrlEntity> findAllByFlag(@NonNull String flag);
}
