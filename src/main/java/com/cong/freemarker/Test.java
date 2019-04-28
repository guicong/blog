package com.cong.freemarker;

/**
 * @author guicong
 * @description
 * @since 2019-04-26
 */
public class Test {

    public static void main(String[] args) {
        String fileName = "http://localhost:8082/message/static/probimg/20190426/1556265461113.png";
        String start = fileName.substring(0, fileName.lastIndexOf("/"));
        String  str = start.substring(start.lastIndexOf("/") + 1) + fileName.substring(fileName.lastIndexOf("/"));

        System.out.println(str);
    }

}
