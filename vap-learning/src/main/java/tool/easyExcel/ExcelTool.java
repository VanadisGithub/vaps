package tool.easyExcel;

import com.alibaba.excel.EasyExcel;

/**
 * kaka.ExcelTool
 *
 * @author yy287502@alibaba-inc.com
 * @date 2020/11/29 3:09 下午
 */
public class ExcelTool {

    public static void main(String[] args) {
        String fileName = "/Users/yaoyuan/Downloads/111.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ReadData.class, new ExcelListener()).sheet().doRead();
    }
}
