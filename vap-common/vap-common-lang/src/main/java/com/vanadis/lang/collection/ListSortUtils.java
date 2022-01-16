package com.vanadis.lang.collection;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.google.common.base.Strings;
import com.vanadis.lang.object.ObjectUtils;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-07-25 13:48
 */
public class ListUtils {

    /**
     * 根据字段排序
     *
     * @param list
     * @param fieldName
     * @return
     */
    public <T> List<T> sort(List<T> list, String fieldName, Boolean isDesc) {
        if (Strings.isNullOrEmpty(fieldName)) {
            return list;
        }
        list.sort((o1, o2) -> {

            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }

            Object obj1 = ObjectUtils.invokeGet(o1, fieldName);
            Object obj2 = ObjectUtils.invokeGet(o2, fieldName);

            if (obj1 == null && obj2 == null) {
                return 0;
            }
            if (obj1 == null) {
                return -1;
            }
            if (obj2 == null) {
                return 1;
            }

            if (obj1 instanceof Integer) {
                return ((Integer)obj1).compareTo((Integer)obj2);
            } else if ((obj1 instanceof Long)) {
                return ((Long)obj1).compareTo((Long)obj2);
            } else if ((obj1 instanceof BigDecimal)) {
                return ((BigDecimal)obj1).compareTo((BigDecimal)obj2);
            } else if (obj1 instanceof Date) {
                Long value1 = ((Date)obj1).getTime();
                Long value2 = ((Date)obj2).getTime();
                return value1.compareTo(value2);
            } else {
                return obj1.toString().compareTo(obj2.toString());
            }

        });

        if (isDesc) {
            Collections.reverse(list);
        }

        return list;
    }

}
