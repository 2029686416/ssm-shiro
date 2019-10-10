package com.generator.model;

public class DictVehPlan {
    private String routeCode;

    private Short routeUpdown;

    private String runDate;

    private String planId;

    private String vehCode;

    private String leaveTime;

    private String realLeaveTime;

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode == null ? null : routeCode.trim();
    }

    public Short getRouteUpdown() {
        return routeUpdown;
    }

    public void setRouteUpdown(Short routeUpdown) {
        this.routeUpdown = routeUpdown;
    }

    public String getRunDate() {
        return runDate;
    }

    public void setRunDate(String runDate) {
        this.runDate = runDate == null ? null : runDate.trim();
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public String getVehCode() {
        return vehCode;
    }

    public void setVehCode(String vehCode) {
        this.vehCode = vehCode == null ? null : vehCode.trim();
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime == null ? null : leaveTime.trim();
    }

    public String getRealLeaveTime() {
        return realLeaveTime;
    }

    public void setRealLeaveTime(String realLeaveTime) {
        this.realLeaveTime = realLeaveTime == null ? null : realLeaveTime.trim();
    }
}