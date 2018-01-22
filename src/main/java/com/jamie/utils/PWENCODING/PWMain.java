package com.jamie.utils.PWENCODING;

import org.apache.commons.codec.net.URLCodec;

/**
 * Created by jeonjaebum on 2018. 1. 18..
 */
public class PWMain {
    public static void main(String[] args) throws Exception {

        URLCodec codec = new URLCodec();

        String test = "your_password";
        String enTest = PWUtil.aesEncode(test);
        String deTest = PWUtil.aesDecode(enTest);


        System.out.println(test);
        System.out.println(enTest);
        System.out.println(deTest);

    }
}
