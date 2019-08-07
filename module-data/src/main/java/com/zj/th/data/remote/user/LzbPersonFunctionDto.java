package com.zj.th.data.remote.user;

import java.io.Serializable;


public class LzbPersonFunctionDto implements Serializable {

    /**
     * 功能码
     */
    private String functionCode;
    /**
     * 功能名
     */
    private String functionName;
    private String path;
    /**
     * 具体权限，这个是多个逗号隔开的数据
     */
    private String permissions;

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
