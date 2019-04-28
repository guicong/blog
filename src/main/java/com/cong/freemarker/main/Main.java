package com.cong.freemarker.main;

import com.cong.freemarker.word.DocumentHandler;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guicong
 * @description
 * @since 2019-04-25
 */
public class Main {

    /**
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title","张三的请休假");
        dataMap.put("startTime","2019-04-25 00:00:00");
        dataMap.put("endTime","2019-04-2" +
                "6 00:00:00");
        dataMap.put("days", 1);
        dataMap.put("reason","出差");
        dataMap.put("realName","张三");
        List<Map<String, Object>> approvalList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("approvalName","张三");
        map1.put("status", "发起审批");
        map1.put("approveTime","2019-04-24 00:00:00");
        map1.put("autograph", getImageBase("F:\\张三.png"));
        approvalList.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("approvalName","李四");
        map2.put("status", "通过");
        map2.put("approveTime","2019-04-24 12:00:00");
        map2.put("autograph", getImageBase("F:\\李四.png"));
        approvalList.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("approvalName","王五");
        map3.put("status", "通过");
        map3.put("approveTime","2019-04-24 14:00:00");
        map3.put("autograph", getImageBase("F:\\王五.png"));
        approvalList.add(map3);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("approvalName","张三");
        map4.put("status", "完成审批");
        map4.put("approveTime","2019-04-24 14:00:00");
        map4.put("autograph", getImageBase("F:\\王五.png"));
        approvalList.add(map4);
        dataMap.put("approvalList", approvalList);


        DocumentHandler documentHandler = new DocumentHandler();
        documentHandler.createDoc(dataMap, "E:/outFile.doc");

    }


    public static String getImageBase(String src) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(src);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

}
