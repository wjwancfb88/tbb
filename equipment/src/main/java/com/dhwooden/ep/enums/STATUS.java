package com.dhwooden.ep.enums;

/**
 * Created by kaiwang on 2018/5/15.
 */
public enum STATUS {
    GR("个人", "0"), GS("公司", "1"), XZ("闲置", "2");
    // 成员变量
    private String name;
    private String index;
    // 构造方法
    private STATUS(String name, String index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(String index) {
        for (STATUS c : STATUS.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIndex() {
        return index;
    }
    public void setIndex(String index) {
        this.index = index;
    }
}
