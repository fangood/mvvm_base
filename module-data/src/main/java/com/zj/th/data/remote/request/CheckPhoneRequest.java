package com.zj.th.data.remote.request;

/**
 * 检查手机号是否存在 请求数据
 */
public class CheckPhoneRequest extends Request {

    private String customerAccount;
    private String customerName;
    private String password;
    private String securityCode;
    private String bankName;
    private String branchName;
    private String bankNo;
    private String openid;
    private String jobPositionNo;
    private String account;

    public String getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(String customerAccount) {
        this.customerAccount = customerAccount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getJobPositionNo() {
        return jobPositionNo;
    }

    public void setJobPositionNo(String jobPositionNo) {
        this.jobPositionNo = jobPositionNo;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "CheckPhoneRequest{" +
                "customerAccount='" + customerAccount + '\'' +
                ", customerName='" + customerName + '\'' +
                ", password='" + password + '\'' +
                ", securityCode='" + securityCode + '\'' +
                ", bankName='" + bankName + '\'' +
                ", branchName='" + branchName + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", openid='" + openid + '\'' +
                ", jobPositionNo='" + jobPositionNo + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
