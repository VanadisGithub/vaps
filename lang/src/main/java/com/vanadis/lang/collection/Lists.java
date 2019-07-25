package com.vanadis.lang.collection;

import com.google.common.base.Strings;
import com.vanadis.lang.object.Objects;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-07-25 13:48
 */
public class Lists {

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
        return list.stream().sorted((o1, o2) -> {

            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return isDesc(-1, isDesc);
            }
            if (o2 == null) {
                return isDesc(1, isDesc);
            }

            Object obj1 = Objects.invokeGet(o1, fieldName);
            Object obj2 = Objects.invokeGet(o2, fieldName);

            if (obj1 == null && obj2 == null) {
                return 0;
            }
            if (obj1 == null) {
                return isDesc(-1, isDesc);
            }
            if (obj2 == null) {
                return isDesc(1, isDesc);
            }

            if (obj1 instanceof Integer) {
                return isDesc(((Integer) obj1 - (Integer) obj2), isDesc);
            } else if ((obj1 instanceof Long)) {
                return isDesc(((Long) obj1).compareTo((Long) obj2), isDesc);
            } else if ((obj1 instanceof BigDecimal)) {
                return isDesc(((BigDecimal) obj1).compareTo((BigDecimal) obj2), isDesc);
            } else if (obj1 instanceof Date) {
                Long value1 = ((Date) obj1).getTime();
                Long value2 = ((Date) obj2).getTime();
                return value1.compareTo(value2);
            } else {
                return isDesc(obj1.toString().compareTo(obj2.toString()), isDesc);
            }

        }).collect(Collectors.toList());
    }

    private int isDesc(int result, boolean isDesc) {
        return isDesc ? -result : result;
    }

}
