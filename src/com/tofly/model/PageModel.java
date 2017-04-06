package com.tofly.model;

import java.util.List;

/**
 * 分页组件
 * @author Wang Hefei
 * Created by lenovo on 2017/3/25.
 */
public class PageModel<T> {

    private int totalRecords; //总记录数
    private List<T> list; //结果集
    private int pageNo; //当前页
    private int pageSize; //每页显示多少条

    /**
     * 获取第一页
     * @return 第一页
     */
    public int getTopPageNo() {
        return 1;
    }

    /**
     * 获取上一页
     * @return 上一页
     */
    public int getPreviousPageNo() {
        if (pageNo <= 1) {
            return 1;
        }
        return pageNo - 1;
    }

    /**
     * 获取下一页
     * @return 下一页
     */
    public int getNextPageNo() {
        if (pageNo >= getTotalPages()) {
            return getTotalPages() == 0 ? 1 : getTotalPages();
        }
        return pageNo + 1;
    }

    /**
     * 获取最后一页
     * @return 最后一页
     */
    public int getBottomPageNo() {
        return getTotalPages() == 0 ? 1 : getTotalPages();
    }

    /**
     * 获取总页数
     * @return 总页数
     */
    private int getTotalPages() {
        return (totalRecords + pageSize - 1) / pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
