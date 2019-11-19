package model;

public class HistoryEntry {

    String methodStr;
    Object methodParameter;

    public HistoryEntry(String methodStr, Object methodParameter) {
        this.methodStr = methodStr;
        this.methodParameter = methodParameter;
    }

    public String getMethodStr() {
        return methodStr;
    }

    public void setMethodStr(String methodStr) {
        this.methodStr = methodStr;
    }

    public Object getMethodParameter() {
        return methodParameter;
    }

    public void setMethodParameter(Object methodParameter) {
        this.methodParameter = methodParameter;
    }
}
