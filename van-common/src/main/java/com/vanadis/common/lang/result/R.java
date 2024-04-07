package com.vanadis.common.lang.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class R<T> {
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    private int status = SUCCESS;
    private String message = "success";
    private T data;
}