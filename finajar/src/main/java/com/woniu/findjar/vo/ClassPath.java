package com.woniu.findjar.vo;

/**
 * @author zhangbin21
 * @date 2019/07/10 10:46
 */
public class ClassPath {
    private int no;

    private String path;

    public ClassPath(int no, String path) {
        this.no = no;
        this.path = path;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
