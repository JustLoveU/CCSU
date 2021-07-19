package com.lomtom.website_university;

import com.lomtom.website_university.entity.InfoEntity;
import com.lomtom.website_university.service.InfoService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

//@SpringBootTest
class WebsiteUniversityApplicationTests {

//    @Autowired
//    InfoService infoService;
//
//    @Test
//    void contextLoads() throws IOException {
//        // 第一步：访问读者首页
//        String url1 = "https://search.sina.com.cn/";
//        String url = "?q=%e7%95%99%e5%ad%a6%e6%94%bf%e7%ad%96&c=news&from=&col=&range=title&source=&country=&size=10&stime=&etime=&time=&dpc=0&a=&ps=0&pf=0&page=";
//        for (int i = 21; i < 25; i++) {
//            Document document = Jsoup.connect(url1 + url + i).get();
//
//            // 第二步：解析页面
//
//            Element result = document.getElementById("result");
//
//            //获取a标签
//            Elements a = result.select("h2").select("a");
//
//            List<InfoEntity> list = new Vector<>();
//            for (Element e : a) {
//                try {
//                    Document document1 = Jsoup.connect(e.attr("href")).get();
//                    Elements elementsByClass = document1.getElementsByClass("main-title");
//                    Elements date = document1.getElementsByClass("date");
//                    Elements content_media_p = document1.getElementsByClass("source");
//                    Elements article = document1.getElementsByClass("article");
//                    InfoEntity infoEntity = new InfoEntity();
//                    infoEntity.setTitle(elementsByClass.get(0).text());
//                    infoEntity.setTime(new SimpleDateFormat(" yyyy-MM-dd HH:mm").parse(" " + date.get(0).text().replace("年", "-").replace("月", "-").replace("日", "") + " "));
//                    infoEntity.setContent(article.get(0).toString());
//                    infoEntity.setSource(content_media_p.get(0).text());
//                    infoEntity.setTypeId(2);
//                    infoEntity.setView(0);
//                    infoEntity.setUrl(e.attr("href"));
//                    list.add(infoEntity);
//                } catch (Exception e1) {
//                    System.out.println("出现异常" + i);
//                }
//            }
////            .stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User :: getUserid))), ArrayList::new));
//            //利用java8特性去重后写入数据口 https://www.cnblogs.com/hooly/p/11612811.html
//            infoService.saveBatch(list.stream()
//                    .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(InfoEntity::getTitle))), ArrayList::new)));
//            System.out.println("第" + i + "页写入成功");
//        }
//        System.out.println("over");
//    }
//
//    @Test
//    public void test() throws ParseException, IOException {
//        String url = "https://k.sina.com.cn/article_1732480923_67438f9b02000of4f.html?from=news&subch=onews";
//        Document document1 = Jsoup.connect(url).get();
//        Elements elementsByClass = document1.getElementsByClass("main-title");
//        Elements date = document1.getElementsByClass("date");
//        Elements content_media_p = document1.getElementsByClass("source");
//        Elements article = document1.getElementsByClass("article");
//        InfoEntity infoEntity = new InfoEntity();
//        infoEntity.setTitle(elementsByClass.get(0).text());
//        infoEntity.setTime(new SimpleDateFormat(" yyyy-MM-dd HH:mm").parse(" " + date.get(0).text().replace("年", "-").replace("月", "-").replace("日", "") + " "));
//        infoEntity.setContent(article.get(0).toString());
//        infoEntity.setSource(content_media_p.get(0).text());
//        infoEntity.setTypeId(4);
//        infoEntity.setView(0);
//        infoEntity.setUrl(url);
//        infoService.save(infoEntity);
//    }


}
