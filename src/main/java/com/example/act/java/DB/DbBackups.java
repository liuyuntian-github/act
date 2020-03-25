package com.example.act.java.DB;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DbBackups implements Job {
    private static final SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 备份数据库db
     * @param root
     * @param pwd
     * @param dbName
     * @param backPath
     * @param backName
     */
    public static void dbBackUp(String root,String pwd,String dbName,String backPath,String backName,String ip,String path)  {
        String pathSql = backPath+backName;
        File fileSql = new File(pathSql);
        //创建备份sql文件
        if (!fileSql.exists()){
            try {
                fileSql.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //mysqldump -hlocalhost -uroot -p123456 db > /home/back.sql
        StringBuffer sb = new StringBuffer();
        sb.append(path);
        sb.append("mysqldump");
        sb.append(" -h"+ip);
        sb.append(" -u"+root);
        sb.append(" -p"+pwd);
        sb.append(" "+dbName+" >");
        sb.append(pathSql);
        System.out.println("cmd命令为："+sb.toString());
        Runtime runtime = Runtime.getRuntime();
        System.out.println("开始备份："+dbName);
        try {
            //Process process = runtime.exec("cmd /c"+sb.toString());
            Process process = runtime.exec(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("备份成功!");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String backName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())+".sql";
        String dbUserName="root";
        String dbPassWord="root";
        String dbName="test";
        String backPath="/home/dbsql/";
        String ip="47.94.229.189";
        String path="/usr/bin/";
        try {
            DbBackups.dbBackUp(dbUserName,dbPassWord,dbName,backPath,backName,ip,path);
            //linux 命令
            ///usr/bin/mysqldump -h47.94.229.189 -uroot -proot test >/home/dbsql/2019-12-27-10-30-43.sql
            System.out.println("执行时间为："+backName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
