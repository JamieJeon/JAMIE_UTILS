package com.jamie.utils.FIND;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by jeonjaebum on 2017. 12. 28..
 */
public class FindStringMain {
    //input your filepath
    public static final String filepath = "{yourfilepath}";
    public static void main(String[] args){
        //input your file name
        try (BufferedReader buffer = new BufferedReader(new FileReader(filepath+"{yourfilename}"),1024)){
            //input keyword
            searchKeyword(buffer,"{keyword}");
        } catch (IOException e) {
            //TODO : Do somthing when IOException
            e.printStackTrace();
        }
    }

    private static void searchKeyword(BufferedReader buffer, String s) throws IOException {
        String line = "";
        while((line = buffer.readLine()) != null){
            if(line.contains(s)){
                System.out.println(line);
            }
        }
    }
}
