package com.jamie.utils.REGEX;

/**
 * Created by jeonjaebum on 2017. 12. 29..
 */
public class RegMain {
    public static void main(String[] args) {
        RegExUtil.printCheckBeforeFilter(
                RegExUtil.RegEx.ONLY_NUMBER,
                "asdf1234jkl",
                "1. 숫자만 표현",
                false
        );
        RegExUtil.printCheckResult(
                RegExUtil.RegEx.CHECK_IMAGE_FULLNAME,
                "test.png",
                "\n2. 이미지 파일인지 확인",
                false
        );
        RegExUtil.printCheckResult(
                RegExUtil.RegEx.CHECK_EMAIL,
                "woqja0@mqnic.co.kr",
                "\n3. 이메일 형식 확인",
                false
        );
        RegExUtil.printCheckResult(
                RegExUtil.RegEx.CHECK_PHONE,
                "01098118995",
                "\n4. 핸드폰 번호 형식 확인",
                false
        );
        RegExUtil.printCheckResult(
                RegExUtil.RegEx.CHECK_PASSWORD,
                "1234567890!",
                "\n5. 비밀번호 형식 확인(최소 10자리 이상 : 특수문자 반드시 포함)",
                false
        );
        RegExUtil.printCheckResult(
                RegExUtil.RegEx.CHECK_NUMBER,
                "1234",
                "\n6. 숫자 확인",
                false
        );
        RegExUtil.printCheckResult(
                RegExUtil.RegEx.CHECK_UPPER,
                "asdf",
                "\n7. 대문자 확인",
                false
        );
        RegExUtil.printCheckResult(
                RegExUtil.RegEx.CHECK_LOWER,
                "asdf",
                "\n8. 소문자 확인",
                false
        );
        RegExUtil.printCheckResult(
                RegExUtil.RegEx.CHECK_SPECIAL,
                "!@",
                "\n9. 특수문자 확인(~!@#$%^&*()+)",
                false
        );
    }
}