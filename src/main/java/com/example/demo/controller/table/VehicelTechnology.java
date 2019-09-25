package com.example.demo.controller.table;

import javax.imageio.stream.FileCacheImageInputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class VehicelTechnology {
    public static String mdlpart = "C:\\Users\\ASUS\\Desktop\\车辆技术档案格式.xlsx";

    public static void main(String[] args) throws Exception {
        List<List<String[]>> tabledataList = new ArrayList<>();
        List<String[]> sheet1 = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            sheet1.add(new String[]{"1", "2019-1-1", "50km", "简单维修", "瞎修", "家里修", "没有合格证", "自己登记的"});
        }
        tabledataList.add(sheet1);
        getXlsx(tabledataList);
    }

    public static void getXlsx(List<List<String[]>> tabledataList) throws Exception {
        try (FileInputStream is = new FileInputStream(mdlpart); XSSFWorkbook workBook = new XSSFWorkbook(is)) {
            XSSFSheet sheet1 = workBook.getSheetAt(0);
            List<String[]> table1 = tabledataList.get(0);

            if (table1.size() > 16) {
                //插入行
                Row startRow = sheet1.getRow(2);
                //Row newRow=sheet1.createRow(1);
                //sheet1.copyRows(2,4,sheet1.getLastRowNum(),new CellCopyPolicy());
                System.out.println("总行输:" + sheet1.getLastRowNum());
                System.out.println("修改"+table1.size());
                sheet1.shiftRows(2, sheet1.getLastRowNum(), table1.size() - 16);
                //XSSFSheet中的shiftRows方法在4.1.0版本中存在bug，至今还未修复，所以这一段话必须加上：
                if (sheet1 instanceof XSSFSheet) {
                    XSSFSheet xSSFSheet = (XSSFSheet) sheet1;
                    // correcting bug that shiftRows does not adjusting references of the cells
                    // if row 3 is shifted down, then reference in the cells remain r="A3", r="B3", ...
                    // they must be adjusted to the new row thoug: r="A4", r="B4", ...
                    // apache poi 3.17 has done this properly but had have other bugs in shiftRows.
                    for (int r = xSSFSheet.getFirstRowNum(); r < sheet1.getLastRowNum() + 1; r++) {
                        XSSFRow row = xSSFSheet.getRow(r);
                        if (row != null) {
                            long rRef = row.getCTRow().getR();
                            for (Cell cell : row) {
                                String cRef = ((XSSFCell) cell).getCTCell().getR();
                                ((XSSFCell) cell).getCTCell().setR(cRef.replaceAll("[0-9]", "") + rRef);
                            }
                        }
                    }
//                sheet1.createRow(2);
                }


                //添加完毕复制格式
                for (int j = 0; j < table1.size() - 16; j++) {
                    XSSFRow row = sheet1.createRow(2 + j);
                    copyRow(workBook, sheet1.getRow(sheet1.getLastRowNum() - 2), row, false);
                }
                // end correcting bug

                //填入数据
                int startindex=2;
                int sumcloum = 8;//总列数
                for (int i = 0; i < table1.size(); i++) {
                    Row row = sheet1.getRow(i + startindex);
                    for (int j = 0; j < sumcloum; j++) {
                        Cell cell = row.getCell(j);
                        cell.setCellValue(table1.get(i)[j]);
                    }
                }
//                Long time = System.currentTimeMillis();
//                try (FileOutputStream out = new FileOutputStream("C:\\Users\\ASUS\\Desktop\\车辆技术档案格式"+time + ".xlsx");) {
//                    workBook.write(out);
//                    out.flush();
//                }
            }
        }
    }


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
