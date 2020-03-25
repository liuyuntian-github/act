package com.example.act.test;

import java.util.HashMap;
import java.util.Map;

public  class AAA {
    final Map map = new HashMap<>();

    class CCC {
        public void setNumber() {
            System.out.println(map.containsKey("code"));
            map.put("code", "2222");
            System.out.println(map.containsKey("code"));
        }
    }
    class BBB {
        public  void  toCCC(AAA.CCC ccc) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println("进入线程");
                        ccc.setNumber();
                        sleep(10000);
                        System.out.println("结束进入线程");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
    public void testCallBack() {
        BBB bbb = new BBB();
        CCC ccc = new CCC();
        bbb.toCCC(ccc);
        int code;
        int i=0;
        while (true) {
            //System.out.println(++i);
            if (map.containsKey("code")) {
                code = Integer.parseInt(map.get("code") + "");
                if (code == 2222) {
                    System.out.println(code);
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("开始运行");
        AAA aaa = new AAA();
        aaa.testCallBack();
    }
}


