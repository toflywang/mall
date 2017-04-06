package com.tofly.model.product;

import java.io.Serializable;

/**
 * 上传文件对象
 * @author Wang Hefei
 * Created by lenovo on 2017/3/25.
 */
public class UploadFile implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id; //文件编号
    private String path; //文件路径

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
