package com.example.demo.controller.table;

import com.example.demo.model.BiddingAll;
import com.example.demo.model.HomeAllLedger;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.Color;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class Bidding {
    public static String mdlpart = "G:\\投标信息\\投标系统修改意见\\投标统计.xlsx";
    public static void main(String[] args) throws Exception {
    }
    public static void getXlsx(List<BiddingAll> list, HttpServletResponse response) throws Exception {
        try (FileInputStream is = new FileInputStream(mdlpart); XSSFWorkbook workBook = new XSSFWorkbook(is)) {
            XSSFSheet sheet1 = workBook.getSheetAt(0);

            CellStyle cellStyle = workBook.createCellStyle();
            cellStyle.setWrapText(true);
            XSSFColor myColor = new XSSFColor(Color.BLACK);
            ((XSSFCellStyle) cellStyle).setBorderColor(XSSFCellBorder.BorderSide.TOP,myColor);
            ((XSSFCellStyle) cellStyle).setBorderColor(XSSFCellBorder.BorderSide.BOTTOM,myColor);
            ((XSSFCellStyle) cellStyle).setBorderColor(XSSFCellBorder.BorderSide.LEFT,myColor);
            ((XSSFCellStyle) cellStyle).setBorderColor(XSSFCellBorder.BorderSide.RIGHT,myColor);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 上下居中
            int startRow=3;
            int l=list.size();
            for(int i=0;i<l;i++){
                Row row=sheet1.createRow(startRow+i);
                row.createCell(0).setCellValue(i+1);
                row.createCell(1).setCellValue(i+1);
                row.createCell(2).setCellValue(list.get(i).getBidid());
                row.createCell(3).setCellValue(list.get(i).getProjectname());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // 时间字符串产生方式
                String startDate = format.format(list.get(i).getBidreceivedate()); //
                String endDate = format.format(list.get(i).getBidstopdate()); //
                row.createCell(4).setCellValue(startDate);
                row.createCell(5).setCellValue(endDate);
                row.createCell(6).setCellValue(list.get(i).getBidregion());
                row.createCell(7).setCellValue(list.get(i).getBidoperator());
                row.createCell(8).setCellValue(list.get(i).getBidoprateregion());
                row.createCell(9).setCellValue(list.get(i).getBidservicetype());
                row.createCell(10).setCellValue(list.get(i).getBidworkload());
                row.createCell(11).setCellValue(list.get(i).getBidtargetmoney());
                row.createCell(12).setCellValue(list.get(i).getBidreview());
                row.createCell(13).setCellValue(list.get(i).getBidopenresult());
                row.createCell(14).setCellValue(list.get(i).getBiddiscardreason());
                row.createCell(15).setCellValue(list.get(i).getBidlossreason());
                row.createCell(16).setCellValue(1);
                row.createCell(17).setCellValue(i+1);
                for (int j=0;j<18;j++){
                    row.getCell(j).setCellStyle(cellStyle);
                }
            }
            Long time = System.currentTimeMillis();
            try (
                    FileOutputStream out = new FileOutputStream("C:\\Users\\ASUS\\Desktop\\投标统计表模板"+time + ".xlsx");
            ) {
                String filePath="C:\\Users\\ASUS\\Desktop\\投标统计表模板"+time + ".xlsx";
                workBook.write(out);
                out.close();
                out.flush();
                downloadExcelModle(response,filePath);
            }
        }
    }

    public static void testDownload(HttpServletResponse response, String wordpath) {
        /*File f = new File(pdfpath);
        new ZipTools(new File(f.getName() + ".zip")).zipFiles(f);*///压缩pdf文件
        File file = new File(wordpath);//   1.获取要下载的文件的绝对路径

        String newDname = "投标统计表.xlsx";     //2.获取要下载的文件名

        if (file.exists()) {  //判断文件是否存在
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/xlsx");
            try {
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(newDname
                        .getBytes("UTF-8"), "ISO8859-1"));  //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte[] buff = new byte[1024];    //5.创建数据缓冲区
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                os = response.getOutputStream(); //6.通过response对象获取OutputStream流
                bis = new BufferedInputStream(new FileInputStream(file));     //4.根据文件路径获取要下载的文件输入流
                int i = bis.read(buff);         //7.将FileInputStream流写入到buffer缓冲区
                while (i != -1) {
                    os.write(buff, 0, buff.length); //8.使用将OutputStream缓冲区的数据输出到客户端浏览器
                    os.flush();
                    i = bis.read(buff);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        file.delete();
    }

    public static void downloadExcelModle(HttpServletResponse response,String wordpath) {
        //下载
//        File file = new File("G:\\投标信息\\投标系统修改意见\\投标统计.xlsx");//   1.获取要下载的文件的绝对路径
        File file = new File(wordpath);//   1.获取要下载的文件的绝对路径
        String newDname = "投标统计表.xlsx";     //2.获取要下载的文件名

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
