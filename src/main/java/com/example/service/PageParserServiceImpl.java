package com.example.service;

import cn.hutool.log.StaticLog;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * @ClassName PageParserServiceImpl
 * @Description TODO
 * @Author miaoyi
 * @Date 2020-03-21 13:41
 * @Version 1.0
 **/

@Service
public class PageParserServiceImpl implements PageParserService{
    @Override
    public void parser(Document document) {
        Elements elements = document.getElementsByClass("event_infomation");
        StaticLog.info("body:  {}  ", elements);
    }
}
