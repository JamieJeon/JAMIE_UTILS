package com.jamie.utils.REGEX;

import java.util.regex.Pattern;

/**
 * Created by jeonjaebum on 2017. 12. 29..
 */
public class RegExUtil {

    /**
     * Target String 을 받아 정규식으로 필터링하여 result String 반환
     *
     * @param regEx 정규식
     * @param target 타켓 문자열
     * @return 필터링된 target
     */
    public static String filter(String regEx, String target){
        return target.replaceAll(regEx,"");
    }

    /**
     * Target String 을 받아 정규식에 해당되는 문자열인지 판단
     *
     * @param regEx 정규식
     * @param target 타켓 문자열
     * @return 정규식에 해당되는지 boolean값
     */
    public static boolean check(String regEx, String target){
        return Pattern.matches(regEx, target);
    }

    /**
     * Target String 을 받아 정규식에 해당되는지 출력
    *
     * @param regEx 정규식
     * @param target 타켓 문자열
     * @return 필터링된 target
     */
    public static void printCheckResult(String regEx, String target, String title, boolean isProcess){
        if(!isProcess){
            return;
        }
        System.out.println(title);
        System.out.println("-------------------------------------------------------------");
        System.out.println("Target is '" + target + "'");
        System.out.println("RegEx is '" + regEx + "'");
        System.out.println("-------------------------------------------------------------");
        System.out.println("Result is " + RegExUtil.check(regEx, target));
        System.out.println("-------------------------------------------------------------");
    }

    /**
     * Target String 을 받아 정규식으로 filtering 된 값 출력
     *
     * @param regEx 정규식
     * @param target 타켓 문자열
     * @return 필터링된 target
     */
    public static void printFilterResult(String regEx, String target, String title, boolean isProcess){
        if(!isProcess){
            return;
        }
        System.out.println(title);
        System.out.println("-------------------------------------------------------------");
        System.out.println("Target is '" + target + "'");
        System.out.println("RegEx is '" + regEx + "'");
        System.out.println("-------------------------------------------------------------");
        System.out.println("Result is " + RegExUtil.filter(regEx, target));
        System.out.println("-------------------------------------------------------------");
    }

    /**
     * Target String 을 받아 정규식에 해당되는지 출력하고 false 일 경우 정규식으로 filtering 된 값 출력
     *
     * @param regEx 정규식
     * @param target 타켓 문자열
     * @return 필터링된 target
     */
    public static void printCheckBeforeFilter(String regEx, String target, String title, boolean isProcess){
        if(!isProcess){
            return;
        }
        System.out.println(title);
        System.out.println("-------------------------------------------------------------");
        System.out.println("Target is '" + target + "'");
        System.out.println("RegEx is '" + regEx + "'");
        System.out.println("-------------------------------------------------------------");
        System.out.println("Check result is " + RegExUtil.check(regEx, target));
        if(!RegExUtil.check(regEx, target)){
            System.out.println("Filter result is " + RegExUtil.filter(regEx, target));
        }
        System.out.println("-------------------------------------------------------------");
    }

    /**
     * ^ : 문자열의 시작
     * $ : 문자열의 종료
     * . : 임의의 한 문자 (문자의 종류 가리지 않음) 단, \ 는 넣을 수 없음
     * * : 앞 문자가 없을 수도 무한정 많을 수도 있음
     * + : 앞 문자가 하나 이상
     * ? : 앞 문자가 없거나 하나있음
     * [] : 문자의 집합이나 범위를 나타내며 두 문자 사이는 - 기호로 범위를 나타낸다. []내에서 ^가 선행하여 존재하면 not 을 나타낸다.
     * {} : 횟수 또는 범위를 나타낸다.
     * () : 소괄호 안의 문자를 하나의 문자로 인식
     * | : 패턴 안에서 or 연산을 수행할 때 사용
     * \s : 공백 문자
     * \S : 공백 문자가 아닌 나머지 문자
     * \w : 알파벳이나 숫자
     * \W : 알파벳이나 숫자를 제외한 문자
     * \d : 숫자 [0-9]와 동일
     * \D : 숫자를 제외한 모든 문자
     * \ : 정규표현식 역슬래시(\)는 확장 문자 역슬래시 다음에 일반 문자가 오면 특수문자로 취급하고 역슬래시 다음에 특수문자가 오면 그 문자 자체를 의미
     * (?i) : 앞 부분에 (?i) 라는 옵션을 넣어주면 대소문자를 구분하지 않음
     */

    public class RegEx {
        public static final String ONLY_NUMBER = "[^0-9]";
        public static final String CHECK_IMAGE_FULLNAME = "^\\S+.(?i)(png|jpg|bmp|gif|jpeg)$";
        public static final String CHECK_EMAIL = "^[a-zA-Z0-9_-]*@[a-zA-Z]*.[a-zA-Z]*$";
        public static final String CHECK_PHONE = "^0\\d{2}-?\\d{3,4}-?\\d{4}$";
        public static final String CHECK_PASSWORD = "^[[~!@#$%^&*()+]+|\\S]{10,}$";
        public static final String CHECK_NUMBER = "[0-9]+";
        public static final String CHECK_UPPER = "[A-Z]+";
        public static final String CHECK_LOWER = "[a-z]+";
        public static final String CHECK_SPECIAL = "[~!@#$%^&*()+]+";
    }

}
