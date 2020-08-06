package com.dhwooden.ep.modal;

/**
 * Created by HP on 2017/6/22.
 * 参数
 */
public class Parameter {
    private String key;

    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Parameter(String key, String value){
        this.key = key;
        this.value = value;
    }
}
