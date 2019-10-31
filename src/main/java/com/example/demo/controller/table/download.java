package com.example.demo.controller.table;

import org.apache.commons.compress.utils.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class download {
    static String root="/var/www/html/model/";
    static void downloadExcelModle(HttpServletResponse response, String filename) {
        //下载
//        File file = new File("G:\\投标信息\\投标系统修改意见\\投标统计.xlsx");//   1.获取要下载的文件的绝对路径
        File file = new File(root+filename);//   1.获取要下载的文件的绝对路径
        String newDname = filename;     //2.获取要下载的文件名
        if (file.exists()) {  //判断文件是否存在
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/xlsx");
            try {
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(newDname
                        .getBytes("UTF-8"), "ISO8859-1"));  //3.设置content-disposition响应头控制浏览器以下载的形式打开文件.特别注意，在swagger中会练吗，
                // 但是其实不会乱码。
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte[] buff = new byte[1024];    //5.创建数据缓冲区
            BufferedInputStream bis = null;
            OutputStream os = null;
            OutputStream outputStream = null;
            try {
                FileInputStream inputStream = new FileInputStream(file);
                outputStream = response.getOutputStream();
                IOUtils.copy(inputStream, outputStream);
                response.flushBuffer();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        file.delete();
    }
}
