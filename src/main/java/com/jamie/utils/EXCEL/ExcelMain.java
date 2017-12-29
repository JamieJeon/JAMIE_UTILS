package com.jamie.utils.EXCEL;

import java.util.List;

/**
 * Created by jeonjaebum on 2017. 12. 15..
 */
public class ExcelMain {
    public static void main(String[] args){
        List<SAMPLE> xlsxList = ExcelUtil.getSampleList("EXCEL/SAMPLE.xlsx");

        for (int i = 0; i < xlsxList.size(); i++) {
            System.out.println(xlsxList.get(i).toString());
        }
    }
}
