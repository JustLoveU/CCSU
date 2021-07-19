package com.lomtom.website_university;

import com.csvreader.CsvReader;
import com.lomtom.website_university.entity.UniversityEntity;
import com.lomtom.website_university.service.UniversityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Vector;

/**
 * @Author: LOMTOM
 * @Date: 2020/7/24
 * @Time: 20:21
 * @Email: lomtom@qq.com
 */

//@SpringBootTest
public class exclTest {
//
//    @Autowired
//    UniversityService universityService;
//
//    @Test
//    public void test() {
//        try {
//            String filePath = "D:\\Documents\\留学\\data1.csv";
//            try {
//                List<UniversityEntity> csvList = new Vector<>();
//                CsvReader reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
//                reader.readHeaders(); //跳过表头,不跳可以注释掉
//
//                while (reader.readRecord()) {
//                    UniversityEntity universityEntity = new UniversityEntity();
//                    universityEntity.setName(reader.get(0));
//                    universityEntity.setSetData(reader.get(1));
//                    universityEntity.setLocation(reader.get(2));
//                    universityEntity.setTel(reader.get(3));
//                    universityEntity.setUrl(reader.get(4));
//                    universityEntity.setIntro(reader.get(5));
//                    universityEntity.setPic(reader.get(6));
//                    universityEntity.setView(0);
//                    universityEntity.setStar(5);
//                    csvList.add(universityEntity); //按行读取，并把每一行的数据添加到list集合
//                }
//                reader.close();
//                System.out.println("读取的行数：" + csvList.size());
//                System.out.println(csvList);
//                universityService.saveBatch(csvList);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public static void readCsvFile(String filePath) {
//    }

//    原文链接：https://blog.csdn.net/galen2016/article/details/78119658
}
