package com.jamie.utils.EXCEL;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeonjaebum on 2017. 12. 15..
 */
public class ExcelUtil {

    public static List<SAMPLE> getSampleList(String filePath) {
        // 반환할 객체 List를 생성
        List<SAMPLE> list = new ArrayList<>();

        FileInputStream fis = null;
        XSSFWorkbook workbook = null;

        try {
            // 엑셀파일 위치에서 파일을 읽어온다
            fis= new FileInputStream(ExcelUtil.class.getClassLoader().getResource(filePath).getFile());
            // 엑셀파일 전체 내용(sheet, row, cell)을 담고 있는 객체
            workbook = new XSSFWorkbook(fis);

            //Sheet, Row, Cell, 변환 객체
            XSSFSheet curSheet;
            XSSFRow curRow;
            XSSFCell curCell;
            SAMPLE vo;

            // Sheet 탐색
            for(int sheetIndex = 0 ; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                // 현재 Sheet
                curSheet = workbook.getSheetAt(sheetIndex);
                // row 탐색 for문
                for(int rowIndex=0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
                    // row 2이하는 Title 정보 (엑셀 파일마다 다름)
                    if(rowIndex < 2) {

                    } else {
                        //list 정보
                        curRow = curSheet.getRow(rowIndex);
                        vo = new SAMPLE();
                        String value;
                        // cell 탐색 for 문
                        for(int cellIndex=0;cellIndex<curRow.getPhysicalNumberOfCells(); cellIndex++) {
                            curCell = curRow.getCell(cellIndex);
                            value = "";
                            // cell 스타일이 다르더라도 String으로 반환 받음
                            switch (curCell.getCellType()){
                                case HSSFCell.CELL_TYPE_NUMERIC:
                                    value = curCell.getNumericCellValue()+"";
                                    break;
                                case HSSFCell.CELL_TYPE_STRING:
                                    value = curCell.getStringCellValue()+"";
                                    break;
                                case HSSFCell.CELL_TYPE_BLANK:
                                    value = curCell.getStringCellValue()+"";
                                    break;
                                case HSSFCell.CELL_TYPE_ERROR:
                                    value = curCell.getErrorCellValue()+"";
                                    break;
                                case HSSFCell.CELL_TYPE_FORMULA:
                                    value = curCell.getCellFormula();
                                    break;
                                default:
                                    value = new String();
                                    break;
                            }

                                // 현재 column index에 따라서 vo에 입력
                            switch (cellIndex) {
                                case 0: vo.setNo(Math.round(
                                                Float.parseFloat(
                                                        value.replaceAll("[^0-9.]","").equals("") ? "0" : value.replaceAll("[^0-9.]","")
                                                )
                                        )); break;     // PK
                                case 1: vo.setId(value); break;     // ID
                                case 2: vo.setName(value); break;   // 이름
                                case 3: vo.setAge(value); break;    // 나이
                                case 4: vo.setPhone(value); break;  // 번호
                                case 5: vo.setEmail(value); break;  // 이메일
                                default: break;
                            }
                        }
                        // row의 첫번째 cell값(PK)이 0인 경우 데이터가 없다고 판단, list 추가 제외
                        if(vo.getNo() != 0) {
                            list.add(vo);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } finally {
            try {
                // 사용한 자원은 finally에서 해제
                if( workbook!= null) workbook.close();
                if( fis!= null) fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }
}
