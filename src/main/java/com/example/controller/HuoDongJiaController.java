package com.example.controller;

import cn.hutool.log.StaticLog;
import com.example.module.entity.PageEntity;
import com.example.module.entity.UrlEntity;
import com.example.service.DocumentService;
import com.example.service.PageParserService;
import com.example.service.PageEntityService;
import com.example.service.UrlEntityService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName HuoDongXingController
 * @Description TODO
 * @Author miaoyi
 * @Date 2020-03-13 12:51
 * @Version 1.0
 **/
@RestController
@RequestMapping("huodongjia")
public class HuoDongJiaController {

    private static final String COMMEN_URL = "https://www.huodongjia.com";

    @Autowired
    private UrlEntityService urlEntityService;

    @Autowired
    private PageParserService pageParserService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private PageEntityService pageEntityService;
    @RequestMapping("all")
    public String run(){

        while (true) {
            int errnoCount = 0;
            try {
                // 读url表
                List<UrlEntity> urlEntityList = urlEntityService.findAllByFlag("no");
                urlEntityList.forEach(urlEntity -> {
                    String url = "";
                    url = urlEntity.getUrl();
                    Document document = documentService.getDocument(url);
                    // 存mysql
                    PageEntity pageEntity = new PageEntity();
                    pageEntity
                            .setUrlId(urlEntity.getUrlId())
                            .setFlag("no")
                            .setLocalUrl("")
                            .setUrl(urlEntity.getUrl())
                            .setDocument(document.toString());
                    StaticLog.info("page-manager 下载:  {}  ", urlEntity.getUrl());
                    pageEntityService.savePageEntity(pageEntity);
                    // 修改已经下载标记
                    urlEntityService.updateFlagById("yes", urlEntity.getUrlId());
                });
            }catch (Exception e){
                errnoCount ++;
                StaticLog.info("ERRNO:  报{}个错啦", errnoCount);
                StaticLog.info("ERRNO:  {}", e.toString());
            }

        }
//        return "success";
    }
    @RequestMapping("{urlMd5}")
    public String runByUrlMd5(@PathVariable("urlMd5")String urlMd5){

        return "success";
    }
}
