package com.lomtom.website_university;

import com.lomtom.website_university.entity.InfoEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2020/7/17
 * @Time: 23:29
 * @Email: lomtom@qq.com
 */

//https://blog.csdn.net/qq_36234893/article/details/97567210
public class test {


    public static void main(String[] args) {
    }


//    public static void main(String[] args) throws IOException, ParseException {
//        // 第一步：访问读者首页
//        String url1 = "https://search.sina.com.cn/";
//        String url = "?q=%e7%95%99%e5%ad%a6%e6%94%bf%e7%ad%96&c=news&from=&col=&range=title&source=&country=&size=10&stime=&etime=&time=&dpc=0&a=&ps=0&pf=0&page=";
//        Document document = Jsoup.connect(url1+url+"1").get();
//
//        // 第二步：解析页面
//
//        Element result = document.getElementById("result");
//
//        //获取a标签
//        Elements a = result.select("h2").select("a");
//
//        List<InfoEntity> list = new ArrayList<>();
//        for (Element e:a) {
//            Document document1 = Jsoup.connect(e.attr("href")).get();
//            Elements elementsByClass = document1.getElementsByClass("main-title");
//            Elements date = document1.getElementsByClass("date");
//            Elements content_media_p = document1.getElementsByClass("source");
//            Element article = document1.getElementById("article");
//            InfoEntity infoEntity = new InfoEntity();
//            infoEntity.setTitle(elementsByClass.get(0).text());
//            infoEntity.setTime(new SimpleDateFormat( " yyyy-MM-dd HH:mm" ).parse( " "+date.get(0).text().replace("年","-").replace("月","-").replace("日","") +" " ));
//            infoEntity.setContent(article.toString());
//            infoEntity.setSource(content_media_p.get(0).text());
//            infoEntity.setTypeId(2);
//            infoEntity.setView(0);
//            infoEntity.setUrl(e.attr("href"));
//            list.add(infoEntity);
//        }
//
//
//
//    }
}
