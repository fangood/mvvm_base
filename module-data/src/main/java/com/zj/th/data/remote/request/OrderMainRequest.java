package com.zj.th.data.remote.request;


public class OrderMainRequest extends Request {

    String acceptUser;
    String orderType = "";
    String houseAddress = "";
    String provinceId = "";
    String cityId = "";
    String districtId = "";
    String mulitOrderStatus = "";
    String orderStatus = ""; // 订单状态
    String dateType = "";
    String searchStartDate = "";
    String searchEndDate = "";
    String isAsc = "";
    String pageSize = "";
    String pageIndex = "";
    String order = "";
    String GlobalUserId;
    String searchAddressForApp = "";

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSearchAddressForApp() {
        return searchAddressForApp;
    }

    public void setSearchAddressForApp(String searchAddressForApp) {
        this.searchAddressForApp = searchAddressForApp;
    }

    public String getGlobalUserId() {
        return GlobalUserId;
    }

    public void setGlobalUserId(String globalUserId) {
        GlobalUserId = globalUserId;
    }

    public String getAcceptUser() {
        return acceptUser;
    }

    public void setAcceptUser(String acceptUser) {
        this.acceptUser = acceptUser;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getMulitOrderStatus() {
        return mulitOrderStatus;
    }

    public void setMulitOrderStatus(String mulitOrderStatus) {
        this.mulitOrderStatus = mulitOrderStatus;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getSearchStartDate() {
        return searchStartDate;
    }

    public void setSearchStartDate(String searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public String getSearchEndDate() {
        return searchEndDate;
    }

    public void setSearchEndDate(String searchEndDate) {
        this.searchEndDate = searchEndDate;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
