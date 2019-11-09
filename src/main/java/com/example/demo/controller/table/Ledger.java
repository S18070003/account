package com.example.demo.controller.table;

import com.example.demo.model.HomeAllLedger;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;

import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ledger {
    static String root="/var/www/html/model/";
//    static String root="";
    public static String mdlpart = root+"LedgerModel.xlsx";
    public static void main(String[] args) throws Exception {
    }
    public static void getXlsx(List<HomeAllLedger> list,HttpServletResponse response) throws Exception {
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
                for (int j=0;j<22;j++){
                    row.getCell(j).setCellStyle(cellStyle);
                }
            }
                Long time = System.currentTimeMillis();
                try (
                        FileOutputStream out = new FileOutputStream(root+"合同台账模板"+time + ".xlsx");
                        ) {
                    String filePath=root+"合同台账模板"+time + ".xlsx";
                    workBook.write(out);
                    out.close();
//                    out.flush();
//                    downloadExcelModle(response,filePath);
                    downloadExcelModle(response,filePath);
                }
            }
    }

    public static void downloadExcelModle(HttpServletResponse response,String wordpath) {
        //下载
//        File file = new File("G:\\投标信息\\投标系统修改意见\\投标统计.xlsx");//   1.获取要下载的文件的绝对路径
        File file = new File(wordpath);//   1.获取要下载的文件的绝对路径
        String newDname = "合同台账统计表.xlsx";     //2.获取要下载的文件名
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

//    public  static void downloadExcelModle(HttpServletResponse response,String filePath) {
//        //下载
//        File file = new File(filePath);//   1.获取要下载的文件的绝对路径
//        System.out.println("1");
//        String newDname = "附件4：合同台账模板.xlsx";     //2.获取要下载的文件名
//
//        if (file.exists()) {  //判断文件是否存在
//            System.out.println("2");
//            response.setHeader("content-type", "application/octet-stream");
//            response.setContentType("application/xlsx");
//            try {
//                response.setHeader("Content-Disposition", "attachment;filename=" + new String(newDname
//                        .getBytes("UTF-8"), "ISO8859-1"));  //3.设置content-disposition响应头控制浏览器以下载的形式打开文件.特别注意，在swagger中会练吗，
//                // 但是其实不会乱码。
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            byte[] buff = new byte[1024];    //5.创建数据缓冲区
//            BufferedInputStream bis = null;
//            OutputStream os = null;
//            OutputStream outputStream = null;
//            try {
//                FileInputStream inputStream = new FileInputStream(file);
//                outputStream = response.getOutputStream();
//                IOUtils.copy(inputStream, outputStream);
//                response.flushBuffer();
//                inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (outputStream != null) {
//                    try {
//                        outputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }

    /**
     * 行复制功能
     *
     * @param wb            工作簿
     * @param fromRow       从哪行开始
     * @param toRow         目标行
     * @param copyValueFlag true则连同cell的内容一起复制
     */
    public static void copyRow(Workbook wb, Row fromRow, Row toRow, boolean copyValueFlag) {
        toRow.setHeight(fromRow.getHeight());

        for (Iterator<Cell> cellIt = fromRow.cellIterator(); cellIt.hasNext(); ) {
            Cell tmpCell = cellIt.next();
            Cell newCell = toRow.createCell(tmpCell.getColumnIndex());
            copyCell(wb, tmpCell, newCell, copyValueFlag);
        }

        Sheet worksheet = fromRow.getSheet();

        for (int i = 0; i < worksheet.getNumMergedRegions(); i++) {
            CellRangeAddress cellRangeAddress = worksheet.getMergedRegion(i);
            if (cellRangeAddress.getFirstRow() == fromRow.getRowNum()) {
                CellRangeAddress newCellRangeAddress = new CellRangeAddress(toRow.getRowNum(),
                        (toRow.getRowNum() + (cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow())),
                        cellRangeAddress.getFirstColumn(), cellRangeAddress.getLastColumn());
                worksheet.addMergedRegionUnsafe(newCellRangeAddress);
            }
        }
    }

    /**
     * 复制单元格
     *
     * @param srcCell
     * @param distCell
     * @param copyValueFlag true则连同cell的内容一起复制
     */
    public static void copyCell(Workbook wb, Cell srcCell, Cell distCell, boolean copyValueFlag) {
        CellStyle newStyle = wb.createCellStyle();
        CellStyle srcStyle = srcCell.getCellStyle();

        newStyle.cloneStyleFrom(srcStyle);
        newStyle.setFont(wb.getFontAt(srcStyle.getFontIndex()));

        // 样式
        distCell.setCellStyle(newStyle);

        // 内容
        if (srcCell.getCellComment() != null) {
            distCell.setCellComment(srcCell.getCellComment());
        }

        // 不同数据类型处理
        CellType srcCellType = srcCell.getCellTypeEnum();
        distCell.setCellType(srcCellType);

        if (copyValueFlag) {
            if (srcCellType == CellType.NUMERIC) {
                if (DateUtil.isCellDateFormatted(srcCell)) {
                    distCell.setCellValue(srcCell.getDateCellValue());
                } else {
                    distCell.setCellValue(srcCell.getNumericCellValue());
                }
            } else if (srcCellType == CellType.STRING) {
                distCell.setCellValue(srcCell.getRichStringCellValue());
            } else if (srcCellType == CellType.BLANK) {

            } else if (srcCellType == CellType.BOOLEAN) {
                distCell.setCellValue(srcCell.getBooleanCellValue());
            } else if (srcCellType == CellType.ERROR) {
                distCell.setCellErrorValue(srcCell.getErrorCellValue());
            } else if (srcCellType == CellType.FORMULA) {
                distCell.setCellFormula(srcCell.getCellFormula());
            }
        }
    }
}
