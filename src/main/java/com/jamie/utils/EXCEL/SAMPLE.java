package com.jamie.utils.EXCEL;

/**
 * Created by jeonjaebum on 2017. 12. 15..
 */
public class SAMPLE {
    private int no              = 0; // PK
    private String id           = ""; // ID
    private String name         = ""; // 이름
    private String age          = ""; // 나이
    private String phone        = ""; // 번호
    private String email        = ""; // 이메일

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("NO : " + this.no);
        sb.append(" ,ID : " + this.id);
        sb.append(" ,NAME : " + this.name);
        sb.append(" ,AGE : " + this.age);
        sb.append(" ,PHONE : " + this.phone);
        sb.append(" ,EMAIL : " + this.email);
        return sb.toString();
    }
}
