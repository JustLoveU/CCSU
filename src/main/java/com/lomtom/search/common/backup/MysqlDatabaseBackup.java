package com.lomtom.search.common.backup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: LOMTOM
 * @Date: 2020/10/3
 * @Time: 14:44
 * @Email: lomtom@qq.com
 * MySQL数据库备份
 * 定时备份数据库https://blog.csdn.net/y1534414425/article/details/106801521
 */

@Component
@EnableScheduling
public class MysqlDatabaseBackup {


    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private String port = "3306";

    private String databasename = "easy_abroad";



    @Scheduled(cron = "0 0 0 ? * MON")
    public Boolean dump() throws Exception {
        String backName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        return dataBaseDump("localhost", port, username, password, databasename, backName);
    }

    //mysqldump -h localhost -P3306 -u root -p123456 db > E:/back.sql
    //备份
    public Boolean dataBaseDump(String host, String port, String username, String password, String databasename, String sqlname) throws Exception {
        File file = new File("/root/mysql/backup/");
        if (!file.exists()) {
            file.mkdir();
        }
        File datafile = new File(file + File.separator + sqlname + ".sql");
        if (datafile.exists()) {
            return false;
        }
        //拼接cmd命令
        Process exec = Runtime.getRuntime().exec("/bin/sh -c mysqldump -h" + host + " -P" + port + " -u " + username + " -p" + password + " " + databasename + " > " + datafile);
        if (exec.waitFor() == 0) {
            return true;
        }
        return false;
    }

}