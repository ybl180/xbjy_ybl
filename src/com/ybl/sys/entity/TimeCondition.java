package com.ybl.sys.entity;

import com.ybl.utils.DateUtil;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/3 10:07
 * @desciption
 */
public class TimeCondition {
    /***
     *开始时间
     */
    private String startTime = "1970-01-01";
    /***
     *结束时间
     */
    private String endTime = DateUtil.getDateStr();


    public String getStartTime() {
        return startTime = (this.startTime == null||this.startTime =="") ? "1970-01-01" : this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime = (this.endTime == ""||this.endTime == null) ? DateUtil.getDateStr() : this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}
