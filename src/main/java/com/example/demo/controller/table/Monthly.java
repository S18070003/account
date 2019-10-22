package com.example.demo.controller.table;


import com.example.demo.model.MonthlyDownload;
import com.example.demo.model.NewProjectInfo;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

public class Monthly {
    private  static String mdlpart="月报信息表.xlsx";
    public static void getExcel(List<MonthlyDownload> list, List<NewProjectInfo>  list1,HttpServletResponse response) throws Exception{
        try (FileInputStream is = new FileInputStream(mdlpart); XSSFWorkbook workBook = new XSSFWorkbook(is)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // 时间字符串产生方式
////第二页
            XSSFSheet sheet2=workBook.getSheet("市场信息月报新");
            int startRow1=4;
            int startRow2=9;
            int startRow3=14;
            int startRow4=19;
            int colomn1=3;
            int l=list.size();
            int l2=list1.size();
            System.out.println(l2);
//
/////复制行
            sheet2.shiftRows(startRow1+1, sheet2.getLastRowNum(), l,true,false);
            sheet2.shiftRows(startRow2 +l+1, sheet2.getLastRowNum(), l2,true,false);
            if (sheet2 instanceof XSSFSheet) {
                XSSFSheet xSSFSheet = (XSSFSheet) sheet2;
                for (int r = xSSFSheet.getFirstRowNum(); r < sheet2.getLastRowNum() + 1; r++) {
                    XSSFRow row = xSSFSheet.getRow(r);
                    if (row != null) {
                        long rRef = row.getCTRow().getR();
                        for (Cell cell : row) {
                            String cRef = ((XSSFCell) cell).getCTCell().getR();
                            ((XSSFCell) cell).getCTCell().setR(cRef.replaceAll("[0-9]", "") + rRef);
                        }
                    }
                }

            }

            for (int j = 0; j < l ; j++) {
                    XSSFRow row = sheet2.createRow(startRow1 + j+1);
                    copyRow(workBook, sheet2.getRow(startRow1), row, false);
            }
//
////添加数据
            for(int i=0;i<l;i++){
                Row row = sheet2.getRow(i + startRow1);
                row.setHeightInPoints(60);
                Cell cell0 = row.getCell(0);
                cell0.setCellValue(i+1);
                Cell cell1 = row.getCell(1);
                cell1.setCellValue(list.get(i).getProjectname());
                Cell cell2 = row.getCell(2);
                String content="项目基本情况："+list.get(i).getProjectgeneral()+"\n"+
                        "最新进展："+list.get(i).getProjectcurrent()+"\n"+
                        "存在问题："+list.get(i).getProjectdifficulty()+"\n"+
                        "下一步工作计划："+list.get(i).getProjectplan();
                cell2.setCellValue(content);
            }


//            第二部分
/////复制行
//            if (sheet2 instanceof XSSFSheet) {
//                XSSFSheet xSSFSheet = (XSSFSheet) sheet2;
//                for (int r = xSSFSheet.getFirstRowNum(); r < sheet2.getLastRowNum() + 1; r++) {
//                    XSSFRow row = xSSFSheet.getRow(r);
//                    if (row != null) {
//                        long rRef = row.getCTRow().getR();
//                        for (Cell cell : row) {
//                            String cRef = ((XSSFCell) cell).getCTCell().getR();
//                            ((XSSFCell) cell).getCTCell().setR(cRef.replaceAll("[0-9]", "") + rRef);
//                        }
//                    }
//                }
//            }
            for (int j = 0; j < l2 ; j++) {
                    XSSFRow row = sheet2.createRow(startRow2+j+l+1);
                    copyRow(workBook, sheet2.getRow(5), row, false);
            }

//
////添加数据
            for(int i=0;i<l2;i++){
                Row row1 = sheet2.getRow(i+startRow2+l);
                row1.setHeightInPoints(60);
                Cell cell0 = row1.getCell(0);
                cell0.setCellValue(i+1);
                Cell cell1 = row1.getCell(1);
                cell1.setCellValue(list1.get(i).getProjectname());
                Cell cell2 = row1.getCell(2);
                String content="项目基本情况："+list1.get(i).getProjectgeneral()+"\n"+
                        "事业部意见："+list1.get(i).getProjectgeneral()+"\n"+
                        "存在问题："+list1.get(i).getProjectproblem();
                cell2.setCellValue(content);
            }
//            第三部分

            Long time = System.currentTimeMillis();
            try (
                    FileOutputStream out = new FileOutputStream("C:\\Users\\ASUS\\Desktop\\月报信息表"+time + ".xlsx");
            ) {
                String filePath="C:\\Users\\ASUS\\Desktop\\月报信息表"+time + ".xlsx";
                workBook.write(out);
                out.close();
                downloadExcelModle(response,filePath);
            }
        }
    }
    public static void downloadExcelModle(HttpServletResponse response,String wordpath) {
        //下载
//        File file = new File("G:\\投标信息\\投标系统修改意见\\投标统计.xlsx");//   1.获取要下载的文件的绝对路径
        File file = new File(wordpath);//   1.获取要下载的文件的绝对路径
        String newDname = "月报信息表.xlsx";     //2.获取要下载的文件名
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
