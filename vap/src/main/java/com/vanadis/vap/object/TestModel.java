package com.vanadis.vap.object;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-08-26 00:41
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TestModel {

    //    @VapJsonSerialize(floatNum = 3)
    private String string;

    private int num1;

    private Integer num2;

    private boolean b1;

    private Boolean b2;

    private List<String> list1;

}
