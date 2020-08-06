package com.dhwooden.ep.enums;

/**
 * Created by kaiwang on 2018/5/15.
 */
public enum TYPE {
    HC("笔记本电脑", "0"), TC("台式电脑", "1"), DYJ("打印机", "2"), OTHER("其他", "3");
    // 成员变量
    private String name;
    private String index;
    // 构造方法
    private TYPE(String name, String index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(String index) {
        for (TYPE c : TYPE.values()) {
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
