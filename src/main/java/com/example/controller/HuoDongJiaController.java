package com.example.controller;

import cn.hutool.log.StaticLog;
import com.example.module.entity.PageEntity;
import com.example.module.entity.UrlEntity;
import com.example.service.PageParserService;
import com.example.service.PageEntityService;
import com.example.service.UrlEntityService;
import com.example.service.getDocumentService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.soap.Text;
import java.util.List;
import java.util.UUID;

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
    private getDocumentService getDocumentService;

    @Autowired
    private PageEntityService pageEntityService;
    @RequestMapping("all")
    public String run(){
        // 读url表
        List<UrlEntity> urlEntityList = urlEntityService.findAllByFlag("no");
        urlEntityList.forEach(urlEntity -> {
            String url = "";
            url = urlEntity.getUrl();
            Document document = getDocumentService.getDocument(url);
            // 存mysql
            PageEntity pageEntity = new PageEntity();
            pageEntity
                    .setId(UUID.randomUUID().toString())
                    .setFlag("no")
                    .setUrlId(urlEntity.getId())
                    .setLocalUrl("")
                    .setUrl(urlEntity.getUrl())
                    .setDocument(document.toString());
            StaticLog.info("page-manager body:  {}  ", document.toString());
            pageEntityService.savePageEntity(pageEntity);
            // 修改已经下载标记
            urlEntityService.updateFlagById("yes",pageEntity.getId());
        });
        return "success";
    }
    @RequestMapping("{urlMd5}")
    public String runByUrlMd5(@PathVariable("urlMd5")String urlMd5){

        return "success";
    }
}
