package model;

public class HistoryEntry {

    String methodStr;
    Object methodParameter;
    Object secondMethodParameter;

    public HistoryEntry(String methodStr, Object methodParameter, Object secondMethodParameter) {
        this.methodStr = methodStr;
        this.methodParameter = methodParameter;
        this.secondMethodParameter = secondMethodParameter;
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

    public Object getSecondMethodParameter() {
        return secondMethodParameter;
    }

    public void setSecondMethodParameter(Object secondMethodParameter) {
        this.secondMethodParameter = secondMethodParameter;
    }

//    @Override
//    public boolean equals(Object obj) {
//        HistoryEntry tmp = (HistoryEntry) obj;
//        return this.getMethodStr().equals(tmp.methodStr) && this.getMethodParameter().equals(tmp.methodParameter) && this.getSecondMethodParameter().equals(tmp.getSecondMethodParameter());
//    }
}
