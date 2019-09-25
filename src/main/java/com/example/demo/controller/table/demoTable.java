package com.example.demo.controller.table;

import ch.qos.logback.core.pattern.color.BlackCompositeConverter;
import com.example.demo.entity.ContractLedger;
import com.example.demo.model.HomeAllLedger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.swing.plaf.synth.Region;
import java.awt.Color;
import java.awt.font.NumericShaper;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

public class demoTable {
    public static void main(String[] args) throws IOException
    {
        System.out.println("TS");
    }
    public static void Excel(List<HomeAllLedger> list)throws IOException{
        String mdlpart="G:\\投标信息\\投标系统修改意见\\附件4：合同台账模板.xlsx";
        String filePath="C:\\Users\\ASUS\\Desktop\\sample1.xlsx";//文件路径
    try(  FileInputStream is = new FileInputStream(mdlpart); XSSFWorkbook workBook = new XSSFWorkbook(is);) {
        XSSFWorkbook workbook = new XSSFWorkbook();//创建Excel文件(Workbook)
        XSSFSheet sheet1 = workbook.createSheet("Test");// 创建工作表(Sheet)
//        cellstyle
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
//        ((XSSFCellStyle) cellStyle).setBorderColor(0,BLACK);
//        cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
//        cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
//        cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 上下居中
//设置标题的风格
        XSSFFont titleFont = workbook.createFont();
        titleFont.setFontHeight(24);
        titleFont.setBold(true);
        CellStyle titleCellStyle = workbook.createCellStyle();
        titleCellStyle.setFont(titleFont);
        titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
        titleCellStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        titleCellStyle.setBorderBottom(BorderStyle.THIN);
        titleCellStyle.setBorderTop(BorderStyle.THIN);
        titleCellStyle.setBorderLeft(BorderStyle.THIN);
        titleCellStyle.setBorderRight(BorderStyle.THIN);
        for (int j=0;j<2;j++){
            Row titleRow=sheet1.createRow(j);
            for(int i=0;i<22;i++){
                Cell titleCell=titleRow.createCell(i);
                titleCell.setCellValue("2019年收入合同台账");
//                titleCell.setCellType(CellType.STRING);
                titleCell.setCellStyle(titleCellStyle);
            }
        }
        CellRangeAddress region = new CellRangeAddress(0, (short) 1, 0, (short) 21);
        //参数1：行号 参数2：起始列号 参数3：行号 参数4：终止列号
        sheet1.addMergedRegion(region);
//        设置列名
        String[] a = new String[]{
                "年度","序号","合同编号","对方合同编号","执行单位","对方执行单位","合同标的","作业区块","签订日期","作业期间","作业期间","预计当年合同金额","预计当年合同金额",
                "预计合同总金额","预计合同总金额","日费","签字人","是否海总集团内部客户","是否正在执行","是否重大金额合同","是否有限公司年度合同","备注"};
        String[] b = new String[]{
                "年度","序号","合同编号","对方合同编号","执行单位","对方执行单位","合同标的","作业区块","签订日期","开始日期","完成日期","人民币","美元",
                "人民币 /万元", "美元 /万美元","日费","签字人","是否海总集团内部客户","是否正在执行","是否重大金额合同","是否有限公司年度合同","备注"};
        Row rowa=sheet1.createRow(2);
        for(int i=0;i<22;i++){
            Cell cell=rowa.createCell(i);
            cell.setCellValue(a[i]);
            cell.setCellStyle(cellStyle);
        }
        Row rowb=sheet1.createRow(3);
        for(int i=0;i<22;i++){
            Cell cell=rowb.createCell(i);
            cell.setCellValue(b[i]);
            cell.setCellStyle(cellStyle);
        }
        CellRangeAddress region0 = new CellRangeAddress(2, (short) 3, 0, (short) 0);
        sheet1.addMergedRegion(region0);
        CellRangeAddress region1 = new CellRangeAddress(2, (short) 3, 1, (short) 1);
        sheet1.addMergedRegion(region1);
        CellRangeAddress region2 = new CellRangeAddress(2, (short) 3, 2, (short) 2);
        sheet1.addMergedRegion(region2);
        CellRangeAddress region3 = new CellRangeAddress(2, (short) 3, 3, (short) 3);
        sheet1.addMergedRegion(region3);
        CellRangeAddress region4 = new CellRangeAddress(2, (short) 3, 4, (short) 4);
        sheet1.addMergedRegion(region4);
        CellRangeAddress region5 = new CellRangeAddress(2, (short) 3, 5, (short) 5);
        sheet1.addMergedRegion(region5);
        CellRangeAddress region6 = new CellRangeAddress(2, (short) 3, 6, (short) 6);
        sheet1.addMergedRegion(region6);
        CellRangeAddress region7 = new CellRangeAddress(2, (short) 3, 7, (short) 7);
        sheet1.addMergedRegion(region7);
        CellRangeAddress region8 = new CellRangeAddress(2, (short) 3, 8, (short) 8);
        sheet1.addMergedRegion(region8);
        CellRangeAddress region9 = new CellRangeAddress(2, (short) 2, 9, (short) 10);
        sheet1.addMergedRegion(region9);
        CellRangeAddress region10 = new CellRangeAddress(2, (short) 2, 11, (short) 12);
        sheet1.addMergedRegion(region10);
        CellRangeAddress region11 = new CellRangeAddress(2, (short) 2, 13, (short) 14);
        sheet1.addMergedRegion(region11);
        CellRangeAddress region12 = new CellRangeAddress(2, (short) 3, 15, (short) 15);
        sheet1.addMergedRegion(region12);
        CellRangeAddress region13 = new CellRangeAddress(2, (short) 3, 16, (short) 16);
        sheet1.addMergedRegion(region13);
        CellRangeAddress region14 = new CellRangeAddress(2, (short) 3, 17, (short) 17);
        sheet1.addMergedRegion(region14);
        CellRangeAddress region15 = new CellRangeAddress(2, (short) 3, 18, (short) 18);
        sheet1.addMergedRegion(region15);
        CellRangeAddress region16 = new CellRangeAddress(2, (short) 3, 19, (short) 19);
        sheet1.addMergedRegion(region16);
        CellRangeAddress region17 = new CellRangeAddress(2, (short) 3, 20, (short) 20);
        sheet1.addMergedRegion(region17);
        CellRangeAddress region18 = new CellRangeAddress(2, (short) 3, 21, (short) 21);
        sheet1.addMergedRegion(region18);
        CellRangeAddress region19 = new CellRangeAddress(2, (short) 3, 22, (short) 22);
        sheet1.addMergedRegion(region19);
//        设置数据
        int startRow=4;
        int l=list.size();
        for(int i=0;i<l;i++){
            Row row=sheet1.createRow(startRow+i);
            row.createCell(0).setCellValue(2019);
            row.createCell(1).setCellValue(i+1);
            row.createCell(2).setCellValue(list.get(i).getContractid());
            row.createCell(3).setCellValue(list.get(i).getContractid2());
            row.createCell(4).setCellValue(list.get(i).getContractdepartment());
            row.createCell(5).setCellValue(list.get(i).getContractopdepartment());
            row.createCell(6).setCellValue(list.get(i).getContractobject());
            row.createCell(7).setCellValue(list.get(i).getBidoprateregion());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // 时间字符串产生方式
            String signDate = format.format(list.get(i).getContractsigndate()); //
            String startDate = format.format(list.get(i).getConstructionstartdate()); //
            String endDate = format.format(list.get(i).getConstructionenddate()); //
            row.createCell(8).setCellValue(signDate);
            row.createCell(9).setCellValue(startDate);
            row.createCell(10).setCellValue(endDate);
            row.createCell(11).setCellValue(list.get(i).getContractmoneypredict0cny());
            row.createCell(12).setCellValue(list.get(i).getContractmoneypredict0usd());
            row.createCell(13).setCellValue(list.get(i).getContractmoneypredicttotal0rmb());
            row.createCell(14).setCellValue(list.get(i).getContractmoneypredicttotal0usd());
            row.createCell(15).setCellValue(list.get(i).getDailycostrmb());
            row.createCell(16).setCellValue(list.get(i).getContractsignperson());
            row.createCell(17).setCellValue(list.get(i).getContractbelongcnooc());
            row.createCell(18).setCellValue(list.get(i).getContractinprocess());
            row.createCell(19).setCellValue(list.get(i).getContractsignificant());
            row.createCell(20).setCellValue(list.get(i).getContractyearlycnooc());
            row.createCell(21).setCellValue(list.get(i).getComments());
//            for(int j=0;j<22;j++){
//                Cell cell=row.createCell(j);
//                cell.setCellValue("123");
//                cell.setCellStyle(cellStyle);
//            }
        }
        FileOutputStream out = new FileOutputStream(filePath);
        workbook.write(out);//保存Excel文件
        out.close();//关闭文件流
        System.out.println("OK!");
    }
//        sheet.setDefaultColumnWidth((short)15);//设置长度
    }
}
