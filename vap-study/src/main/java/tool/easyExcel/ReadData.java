package tool.easyExcel;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

//设置表头和添加的数据字段

@Data
public class ReadData {

    //设置表头名称

    @ExcelProperty("门店")
    private String store;

    //设置表头名称
    @ExcelProperty("实际支付金额")
    private Integer money;

    @ExcelProperty("商品名称")
    private String goodsname;

}
