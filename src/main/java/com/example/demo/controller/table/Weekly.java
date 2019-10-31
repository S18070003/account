package com.example.demo.controller.table;

import com.example.demo.model.HomeAllLedger;
import com.example.demo.model.WeeklyModel;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.Color;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Weekly {
    static String root="/var/www/html/model/";
    private  static String mdlpart=root+"WeeklyModel.xlsx";
    public static void getExcel(List<WeeklyModel> list, String name,HttpServletResponse response) throws Exception{
        try (FileInputStream is = new FileInputStream(mdlpart); XSSFWorkbook workBook = new XSSFWorkbook(is)) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // 时间字符串产生方式
//第一页
            XSSFSheet sheet1 = workBook.getSheetAt(0);
            XSSFRow row0=sheet1.getRow(14);
            XSSFCell cell00=row0.getCell(6);
            XSSFCellStyle style=cell00.getCellStyle();
            XSSFCell cell01=row0.createCell(7);
            cell01.setCellValue(name);
            cell01.setCellStyle(style);
            XSSFRow row1=sheet1.getRow(16);
            XSSFCell cell02=row1.createCell(7);
            cell02.setCellValue(format.format(new Date()));
            cell02.setCellStyle(style);

//第二页
            XSSFSheet sheet2=workBook.getSheetAt(1);
            int startRow=2;
            int l=list.size();
 /////复制行
            sheet2.shiftRows(2, sheet2.getLastRowNum(), l,true,false);
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
///复制行
                for (int j = 0; j < l ; j++) {
                        XSSFRow row = sheet2.createRow(startRow + j);
                        copyRow(workBook, sheet2.getRow(1), row, false);
                }

//添加数据
            Row rowFirst=sheet2.getRow(0);
                Cell cellfirst=rowFirst.getCell(0);
                cellfirst.setCellValue("油田生产事业部市场信息周报"+"（"+format.format(new Date())+"）");
            for(int i=0;i<l;i++){
                Row row = sheet2.getRow(i + 2);
                Cell cell0 = row.getCell(0);
                cell0.setCellValue(i+1);
                Cell cell1 = row.getCell(1);
                cell1.setCellValue(list.get(i).getDepartment());
                Cell cell2 = row.getCell(2);
                cell2.setCellValue(list.get(i).getBidregion());
                Cell cell3 = row.getCell(3);
                cell3.setCellValue(list.get(i).getProjectname());
                Cell cell4 = row.getCell(4);
                cell4.setCellValue(list.get(i).getProjectgeneral());
                Cell cell5 = row.getCell(5);
                cell5.setCellValue(list.get(i).getProjectcurrent());
                Cell cell6 = row.getCell(6);
                cell6.setCellValue(list.get(i).getProjectestimateamount());
                Cell cell7 = row.getCell(7);
                cell7.setCellValue(list.get(i).getProjectcharger());
                Cell cell8 = row.getCell(8);
                String startDate = format.format(list.get(i).getProjectstarttime()); //
                cell8.setCellValue(startDate);
                Cell cell9 = row.getCell(9);
                cell9.setCellValue(list.get(i).getProjectplan());
                Cell cell10 = row.getCell(10);
                cell10.setCellValue(list.get(i).getProjectplan());
                Cell cell11 = row.getCell(11);
                cell11.setCellValue(list.get(i).getProjectdifficulty());
                Cell cell12 = row.getCell(11);
                cell12.setCellValue(list.get(i).getProjectsuggestion());
            }
            int x=sheet2.getLastRowNum();
            System.out.println(x);
            XSSFRow rowlast=sheet2.getRow(x-9);
            Cell celllast=rowlast.getCell(11);
            celllast.setCellValue(format.format(new Date()));

            Long time = System.currentTimeMillis();
            try (
                    FileOutputStream out = new FileOutputStream(root+"市场信息周报模板"+time + ".xlsx");
            ) {
                String filePath=root+"市场信息周报模板"+time + ".xlsx";
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
        String newDname = "市场信息周报模板.xlsx";     //2.获取要下载的文件名
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
