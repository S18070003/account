package com.example.demo.controller.table;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.*;

public class GPS {
    public static String mdlpath = "车辆GPS运行基本情况抽查表.docx";

    public static void main(String[] args) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, Object> pic = new HashMap<String, Object>();
        pic.put("${pic1}","sign.png");
        pic.put("${b}","sign.png");

        data.put("${f}","1");
        data.put("${g}","2");
        data.put("${h}","3");


        List<List<String[]>> tabledataList=new ArrayList<>();
        List<String[]> table1=new ArrayList<>();
        for(int i=0;i<30;i++){
            table1.add(new String[]{"1","在线","否","否","否","病假"});
        }
        tabledataList.add(table1);
        getWord(data,tabledataList,pic);
    }

    public static void getWord(Map<String, Object> data, List<List<String[]>> tabledataList,Map<String,Object> picmap) throws Exception{
        try (FileInputStream is = new FileInputStream(mdlpath); XWPFDocument document = new XWPFDocument(is)) {
            //替换掉表格之外的文本对象(仅限文本)
            changeText(document, data);
            System.out.println(2);
            //替换表格内的文本对象
            changeTableText(document, data);

            //替换图片
            changePic(document,picmap);
            changeTablePic(document,picmap);

            //开始填入表格数据.需要根据自己的需求更改代码。
            List<String[]> table1data = tabledataList.get(0);
            XWPFTable table1 = document.getTableArray(0);
            if (table1data.size() > 24) {
                //动态添加行数
                XWPFTableRow row1 = table1.getRow(1);
                for (int i = 0; i < table1data.size() - 1; i++) {
                    copy(table1, row1,  1 );//复制到最后面
                }
            }
            //填充
            List<XWPFTableRow> rowList = table1.getRows();
            for (int i = 0; i < table1data.size(); i++) {
                XWPFTableRow row = rowList.get(i + 1);//从第1行开始
                List<XWPFTableCell> cellList = row.getTableCells();
                String[] rowdata = table1data.get(i);
                for (int j = 0; j < cellList.size(); j++) {
                    XWPFTableCell cell = cellList.get(j);
                    //当前cell的索引是第i行第j列;
                    XWPFParagraph paragraph = cell.getParagraphArray(0);
                    if (paragraph.getRuns().size() == 0)
                        paragraph.createRun();
                    for (XWPFRun run : paragraph.getRuns()) {
                        run.setText(rowdata[j]);
                    }
                }
            }

            //合并单元格
//            if(table1data.size()>1){
//                mergeCellVertically(table1,0,12,11+table1data.size());
//                mergeCellVertically(table1,1,12,11+table1data.size());
//            }


            long time=System.currentTimeMillis();
            try(FileOutputStream out=new FileOutputStream(time+".docx")){
                document.write(out);
            }
        }
    }

    /**
     * 替换段落文本
     *
     * @param document docx解析对象
     * @param textMap  需要替换的信息集合
     */
    public static void changeText(XWPFDocument document, Map<String, Object> textMap) {
        //获取段落集合
        List<XWPFParagraph> paragraphs = document.getParagraphs();

        for (XWPFParagraph paragraph : paragraphs) {
            //判断此段落时候需要进行替换
            String text = paragraph.getText();
            if (checkText(text)) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    //替换模板原来位置
                    Object ob = changeValue(run.toString(), textMap);
                    //System.out.println("段落：" + run.toString());
                    if (ob instanceof String) {
                        if(textMap.containsKey(run.toString())){
                            run.setText((String) ob, 0);
                        }
                    }
                }
            }
        }
    }
    public static void changePic(XWPFDocument document, Map<String, Object> textMap) throws Exception{
        //获取段落集合
        List<XWPFParagraph> paragraphs = document.getParagraphs();

        for (XWPFParagraph paragraph : paragraphs) {
            //判断此段落时候需要进行替换
            String text = paragraph.getText();
            if (checkText(text)) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    //替换模板原来位置
                    Object ob = changeValue(run.toString(), textMap);
                    //System.out.println("段落：" + run.toString());
                    if (ob instanceof String) {
                        if(textMap.containsKey(run.toString())){
                            run.setText("", 0);
                            try(FileInputStream is=new FileInputStream((String)ob)){
                                run.addPicture(is,XWPFDocument.PICTURE_TYPE_PNG,(String)ob, Units.toEMU(50),Units.toEMU(50));
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean checkText(String text) {
        boolean check = false;
        if (text.indexOf("$") != -1) {
            check = true;
        }
        return check;
    }

    public static void changeTableText(XWPFDocument document, Map<String, Object> data) {
        List<XWPFTable> tableList = document.getTables();

        //循环所有需要进行替换的文本，进行替换
        for (int i = 0; i < tableList.size(); i++) {
            XWPFTable table = tableList.get(i);
            if (checkText(table.getText())) {
                List<XWPFTableRow> rows = table.getRows();
                System.out.println("简单表格替换：" + rows);
                //遍历表格,并替换模板
                eachTable(document, rows, data);
            }
        }
    }
    public static void changeTablePic(XWPFDocument document, Map<String, Object> pic) throws Exception{
        List<XWPFTable> tableList = document.getTables();

        //循环所有需要进行替换的文本，进行替换
        for (int i = 0; i < tableList.size(); i++) {
            XWPFTable table = tableList.get(i);
            if (checkText(table.getText())) {
                List<XWPFTableRow> rows = table.getRows();
                System.out.println("简单表格替换：" + rows);
                //遍历表格,并替换模板
                eachTablePic(document, rows, pic);
            }
        }
    }
    public static void eachTablePic(XWPFDocument document, List<XWPFTableRow> rows, Map<String, Object> pic) throws Exception{
        for (XWPFTableRow row : rows) {
            List<XWPFTableCell> cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
                //判断单元格是否需要替换
                if (checkText(cell.getText())) {
                    //System.out.println("cell:" + cell.getText());
                    List<XWPFParagraph> paragraphs = cell.getParagraphs();
                    for (XWPFParagraph paragraph : paragraphs) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            Object ob = changeValue(run.toString(), pic);
                            if (ob instanceof String) {
                                System.out.println("run:"+"'"+run.toString()+"'");
                                if(pic.containsKey(run.toString())){
                                    System.out.println("run:"+run.toString()+"替换为"+(String)ob);
                                    run.setText("", 0);
                                    try(FileInputStream is=new FileInputStream((String)ob)){
                                        run.addPicture(is,XWPFDocument.PICTURE_TYPE_PNG,(String)ob,Units.toEMU(50),Units.toEMU(100));
                                    }
                                }
                                else{
                                    System.out.println("'"+run.toString()+"'不匹配");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static Object changeValue(String value, Map<String, Object> textMap) {
        Set<Map.Entry<String, Object>> textSets = textMap.entrySet();
        Object valu = "";
        for (Map.Entry<String, Object> textSet : textSets) {
            //匹配模板与替换值 格式${key}
            String key = textSet.getKey();
            if (value.indexOf(key) != -1) {
                valu = textSet.getValue();
            }
        }
        return valu;
    }

    public static void eachTable(XWPFDocument document, List<XWPFTableRow> rows, Map<String, Object> textMap) {
        for (XWPFTableRow row : rows) {
            List<XWPFTableCell> cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
                //判断单元格是否需要替换
                if (checkText(cell.getText())) {
                    //System.out.println("cell:" + cell.getText());
                    List<XWPFParagraph> paragraphs = cell.getParagraphs();
                    for (XWPFParagraph paragraph : paragraphs) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {

                            Object ob = changeValue(run.toString(), textMap);
                            if (ob instanceof String) {

                                System.out.println("run:"+"'"+run.toString()+"'");
                                if(textMap.containsKey(run.toString())){
                                    System.out.println("run:"+run.toString()+"替换为"+(String)ob);
                                    run.setText((String) ob, 0);
                                }
                                else{
                                    System.out.println("'"+run.toString()+"'不匹配");
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    public static void insertTable(XWPFTable table, List<String> tableList, List<String[]> daList, Integer type) {
        if (2 == type) {
            //创建行和创建需要的列
            for (int i = 0; i < daList.size() - 1; i++) {
                //添加一个新行
//                XWPFTableRow row = table.insertNewTableRow(1 + i);
                XWPFTableRow row = table.getRow(1);
                copy(table, row, i + 1);
//                for (int k = 0; k < daList.get(i).length; k++) {
//                    row.createCell();
//                }
            }
            System.out.println("插入表格数据");
            //创建行,根据需要插入的数据添加新行，不处理表头
            for (int i = 0; i < daList.size(); i++) {
                List<XWPFTableCell> cells = table.getRow(i + 1).getTableCells();
                for (int j = 0; j < cells.size(); j++) {
                    XWPFTableCell cell02 = cells.get(j);
                    cell02.setText(daList.get(i)[j]);
                }
            }
        } else if (4 == type) {
            //插入表头下面第一行的数据
            for (int i = 0; i < tableList.size(); i++) {
                XWPFTableRow row = table.createRow();
                List<XWPFTableCell> cells = row.getTableCells();
                cells.get(0).setText(tableList.get(i));
            }
        }
    }

    /**
     * 复制两行
     *
     * @param table
     * @param sourceRow
     * @param rowIndex
     */
    public static void copy(XWPFTable table, XWPFTableRow sourceRow, int rowIndex) {
        //在表格指定位置新增一行
        XWPFTableRow targetRow = table.insertNewTableRow(rowIndex);
        //复制行属性
        targetRow.getCtRow().setTrPr(sourceRow.getCtRow().getTrPr());
        List<XWPFTableCell> cellList = sourceRow.getTableCells();
        if (null == cellList) {
            return;
        }
        //复制列及其属性和内容
        XWPFTableCell targetCell = null;
        for (XWPFTableCell sourceCell : cellList) {
            targetCell = targetRow.addNewTableCell();
            //列属性
            targetCell.getCTTc().setTcPr(sourceCell.getCTTc().getTcPr());
            //段落属性
            if (sourceCell.getParagraphs() != null && sourceCell.getParagraphs().size() > 0) {
                targetCell.getParagraphs().get(0).getCTP().setPPr(sourceCell.getParagraphs().get(0).getCTP().getPPr());
                if (sourceCell.getParagraphs().get(0).getRuns() != null && sourceCell.getParagraphs().get(0).getRuns().size() > 0) {
                    XWPFRun cellR = targetCell.getParagraphs().get(0).createRun();
                    cellR.setText(sourceCell.getText());
                    cellR.setBold(sourceCell.getParagraphs().get(0).getRuns().get(0).isBold());
                } else {
                    targetCell.setText(sourceCell.getText());
                }
            } else {
                targetCell.setText(sourceCell.getText());
            }
        }
    }

    /**
     * 合并行
     * @param table
     * @param col
     * @param fromRow
     * @param toRow
     */
    public static void mergeCellVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            CTVMerge vmerge = CTVMerge.Factory.newInstance();
            if (rowIndex == fromRow) {
                vmerge.setVal(STMerge.RESTART);
            } else {
                vmerge.setVal(STMerge.CONTINUE);
            }
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            CTTcPr tcPr = cell.getCTTc().getTcPr();
            if (tcPr != null) {
                tcPr.setVMerge(vmerge);
            } else {
                tcPr = CTTcPr.Factory.newInstance();
                tcPr.setVMerge(vmerge);
                cell.getCTTc().setTcPr(tcPr);
            }
        }
    }
    /**
     * 合并列
     *
     * @param table
     * @param row
     * @param fromCell
     * @param toCell
     */
    public void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if (cellIndex == fromCell) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
                //cellCount为表格总列数
                int cellCount = table.getRow(row).getTableCells().size();
                Integer width = (toCell - fromCell + 1) / cellCount * table.getCTTbl().getTblPr().getTblW().getW().intValue();
                cell.getCTTc().getTcPr().addNewTcW().setW(BigInteger.valueOf(width));
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }
}
