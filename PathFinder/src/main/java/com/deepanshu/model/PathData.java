package com.deepanshu.model;

public class PathData {

    private final long id;
    private final String content;

    public PathData(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
