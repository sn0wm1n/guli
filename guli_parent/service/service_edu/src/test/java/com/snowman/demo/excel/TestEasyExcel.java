package com.snowman.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
//        //实现excel写操作
//        //1.设置文件地址和 名称
//        String filename = "A:\\write.xlsx";
//        //2.调用easyexcel里面的方法实现写操作
//        EasyExcel.write(filename, DemoData.class).sheet("学生列表").doWrite(getData());

        //实现excel读操作
        String filename = "A:\\write.xlsx";

        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    private static List<DemoData> getData() {
        ArrayList<DemoData> arr = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setName("学生" + i);
            arr.add(data);
        }
        return arr;
    }
}
