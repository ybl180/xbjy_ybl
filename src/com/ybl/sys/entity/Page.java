package com.ybl.sys.entity;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/2 16:39
 * @desciption
 */
public class Page {
    /**
     * 当前页
     */
    private Integer pageCurrent ;
    /**
     * 每页显示的数量
     */
    private Integer pageSize = 5;
    /**
     * 总记录数
     */
    private Integer count;
    /**
     * 总页数
     */
    private Integer pageCount;

    public Integer getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(Integer pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPageCount() {
        return pageCount = this.count % this.pageSize == 0 ? this.count / this.pageSize : (this.count / this.pageSize + 1);
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
