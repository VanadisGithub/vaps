package com.vanadis.future.hook;

/**
 * StageBaseResult
 *
 * @author yy287502@alibaba-inc.com
 * @date 2021/5/13 11:38 上午
 */
public class StageResult<T> {

    private T result;

    private Long spendTime;

    private String errorInfo;

    public StageResult(T result, Long spendTime, String errorInfo) {
        this.result = result;
        this.spendTime = spendTime;
        this.errorInfo = errorInfo;
    }

    public StageResult success(T result, Long spendTime) {
        return new StageResult(result, spendTime, null);
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Long getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(Long spendTime) {
        this.spendTime = spendTime;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
