package com.lomtom.website_university.common.utils;

import java.util.Random;

/**
 * User: 欧阳隆桐
 * Date: 2020/1/31
 * Time: 18:45
 */
public class GenerateCode {
    static public String generateCode() {
        Random random = new Random();
        char[] chars=new char[6];
        for (int i = 0; i < 6; i++) {
            Character c=null;
            switch (random.nextInt(3)) {
                case 0:
                    c = (char) ('A' + random.nextInt('Z' - 'A' + 1));
                    break;
                case 1:
                    c = (char) ('0' + random.nextInt('9' - '0' + 1));
                    break;
                case 2:
                    c = (char) ('a' + random.nextInt('z' - 'a' + 1));
                    break;
            }
            chars[i]=c;
        }
        return String.valueOf(chars);

    }
}