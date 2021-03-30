package tool.easyExcel;

import com.alibaba.excel.EasyExcel;

/**
 * kaka.ExcelTool
 *
 * @author yaoyuan
 * @date 2020/11/29 3:09 下午
 */
public class ExcelTool {

    public static void main(String[] args) {
        String fileName = "/Users/yaoyuan/Downloads/OpenAPI.xlsx";
        //EasyExcel.read(fileName, new ExcelListener()).sheet(15).doRead();
        EasyExcel.read(fileName, new ExcelListener()).doReadAll();
    }
}
