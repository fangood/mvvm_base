package com.zj.th.data.remote.request;


public class RegisterRequest extends Request {

    private String customerName;
    private String CustomerAccount;
    private String Password;
    private String SecurityCode;
    private String BankName;
    private String BranchName;
    private String BankNo;
    private String Openid;
    private String JobPositionNo;

    public RegisterRequest(String customerAccount, String password, String securityCode) {
        CustomerAccount = customerAccount;
        Password = password;
        SecurityCode = securityCode;
    }

    public RegisterRequest(String customerName, String customerAccount, String password, String securityCode, String jobPositionNo) {
        this(customerAccount, password, securityCode);
        this.customerName = customerName;
        JobPositionNo = jobPositionNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAccount() {
        return CustomerAccount;
    }

    public void setCustomerAccount(String customerAccount) {
        CustomerAccount = customerAccount;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSecurityCode() {
        return SecurityCode;
    }

    public void setSecurityCode(String securityCode) {
        SecurityCode = securityCode;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getBankNo() {
        return BankNo;
    }

    public void setBankNo(String bankNo) {
        BankNo = bankNo;
    }

    public String getOpenid() {
        return Openid;
    }

    public void setOpenid(String openid) {
        Openid = openid;
    }

    public String getJobPositionNo() {
        return JobPositionNo;
    }

    public void setJobPositionNo(String jobPositionNo) {
        JobPositionNo = jobPositionNo;
    }
}
