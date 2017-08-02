package br.com.testiugu.test;

import br.com.tecsofti.architecture.common.util.URLConnectionUtil;

public class IuguTestMain {
    public static void main(String[] args) {
        try {
//            System.out.println(URLConnectionUtil.sendPostRequest("https://api.iugu.com/v1/charge", 30000));
            System.out.println(URLConnectionUtil.sendPostRequest("https://api.iugu.com/v1/charge/", 30000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
