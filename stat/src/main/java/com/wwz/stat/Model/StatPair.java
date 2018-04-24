package com.wwz.stat.Model;

/**
 * Created by alice on 18/4/24.
 */
public class StatPair {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public StatPair(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
