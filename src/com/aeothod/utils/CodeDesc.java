package com.aeothod.utils;

public enum CodeDesc {
    INPUT("/assets/txt/input.txt");// input输入框
    private String url;

    CodeDesc(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
