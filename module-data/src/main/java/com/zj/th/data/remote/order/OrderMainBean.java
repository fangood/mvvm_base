package com.zj.th.data.remote.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderMainBean implements Serializable {


    /**
     * provinceName : 上海市
     * provinceId : 1
     * cityName : 上海市
     * city : 2
     * districtName : 闵行区
     * district : 11
     * villageName : 绿地东上海
     * villageAddress : 秀浦路886弄
     * villageId : 3295
     * summary : {"clearAcceptCount":0,"clearCompleteCount":0,"clearRecordCount":0,"decorationAcceptCount":2,"decorationConstructingCount":2,"decorationCompleteCount":2,"decorationRecordCount":2}
     * enabledAcceptNumber : 3
     * houseInfoList : [{"cucId":90058,"decorationOrderNo":"M201804132241789","houseAddress":"上海市闵行区新飞路1500弄2017号02单元1401室","acceptDate":"2018-05-08 00:00:00","acceptProgress":"26/28","subFinishTime":0,"delayDay":0,"villageName":"绿地东上海","finishDate":"2018-05-15 17:25:07","orderItemNo":"T201804132241825","orderStatus":520,"orderStatusName":"待抢单"},{"cucId":14194,"decorationOrderNo":"M201805132246590","houseAddress":"苏州市昆山市鑫隆广场2018号05单元10室006室","acceptDate":"2018-05-17 14:25:05","acceptProgress":"35/49","subFinishTime":0,"delayDay":0,"villageName":"鑫隆广场","finishDate":null,"orderItemNo":"T201805132246635","orderStatus":200,"orderStatusName":"已发布"}]
     */

    private String provinceName;
    private String provinceId;
    private String cityName;
    private String city;
    private String districtName;
    private String district;
    private String villageName;
    private String villageAddress;
    private String villageId;
    private SummaryBean summary;
    private int enabledAcceptNumber;
    private List<HouseInfoListBean> houseInfoList;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getVillageAddress() {
        return villageAddress;
    }

    public void setVillageAddress(String villageAddress) {
        this.villageAddress = villageAddress;
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }

    public SummaryBean getSummary() {
        return summary;
    }

    public void setSummary(SummaryBean summary) {
        this.summary = summary;
    }

    public int getEnabledAcceptNumber() {
        return enabledAcceptNumber;
    }

    public void setEnabledAcceptNumber(int enabledAcceptNumber) {
        this.enabledAcceptNumber = enabledAcceptNumber;
    }

    public List<HouseInfoListBean> getHouseInfoList() {
        if (houseInfoList == null) {
            return new ArrayList<>();
        }
        return houseInfoList;
    }

    public void setHouseInfoList(List<HouseInfoListBean> houseInfoList) {
        this.houseInfoList = houseInfoList;
    }

    public static class SummaryBean implements Serializable {
        /**
         * clearAcceptCount : 0
         * clearCompleteCount : 0
         * clearRecordCount : 0
         * decorationAcceptCount : 2
         * decorationConstructingCount : 2
         * decorationCompleteCount : 2
         * decorationRecordCount : 2
         */

        private int clearAcceptCount = 0;
        private int clearCompleteCount = 0;
        private int clearRecordCount = 0;
        private int decorationAcceptCount = 0;
        private int decorationConstructingCount = 0;
        private int decorationCompleteCount = 0;
        private int decorationRecordCount = 0;

        public int getClearAcceptCount() {
            return clearAcceptCount;
        }

        public void setClearAcceptCount(int clearAcceptCount) {
            this.clearAcceptCount = clearAcceptCount;
        }

        public int getClearCompleteCount() {
            return clearCompleteCount;
        }

        public void setClearCompleteCount(int clearCompleteCount) {
            this.clearCompleteCount = clearCompleteCount;
        }

        public int getClearRecordCount() {
            return clearRecordCount;
        }

        public void setClearRecordCount(int clearRecordCount) {
            this.clearRecordCount = clearRecordCount;
        }

        public int getDecorationAcceptCount() {
            return decorationAcceptCount;
        }

        public void setDecorationAcceptCount(int decorationAcceptCount) {
            this.decorationAcceptCount = decorationAcceptCount;
        }

        public int getDecorationConstructingCount() {
            return decorationConstructingCount;
        }

        public void setDecorationConstructingCount(int decorationConstructingCount) {
            this.decorationConstructingCount = decorationConstructingCount;
        }

        public int getDecorationCompleteCount() {
            return decorationCompleteCount;
        }

        public void setDecorationCompleteCount(int decorationCompleteCount) {
            this.decorationCompleteCount = decorationCompleteCount;
        }

        public int getDecorationRecordCount() {
            return decorationRecordCount;
        }

        public void setDecorationRecordCount(int decorationRecordCount) {
            this.decorationRecordCount = decorationRecordCount;
        }
    }

    public static class HouseInfoListBean implements Serializable {
        /**
         * cucId : 90058
         * decorationOrderNo : M201804132241789
         * houseAddress : 上海市闵行区新飞路1500弄2017号02单元1401室
         * acceptDate : 2018-05-08 00:00:00
         * acceptProgress : 26/28
         * subFinishTime : 0
         * delayDay : 0
         * villageName : 绿地东上海
         * finishDate : 2018-05-15 17:25:07
         * orderItemNo : T201804132241825
         * orderStatus : 520
         * orderStatusName : 待抢单
         * "expectStartDate": "string"
         * <p>
         * ExpectEndDate 预期完工时间
         */
        String detail;
        String expectStartDate;
        String expectEndDate;

        int tag;

        private int cucId;
        private String decorationOrderNo;
        private String houseAddress;
        private String acceptDate;
        private String acceptProgress;
        private int subFinishTime;
        private int delayDay;
        private String villageName;
        private String finishDate;
        private String orderItemNo;
        private int orderStatus;
        private String orderStatusName;

        /**新增预期开始时间和预期完工时间 2018/08/28*/
        private String itemExpectStartDate;
        private String itemExpectEndDate;

        public String getItemExpectStartDate() {
            return itemExpectStartDate;
        }

        public void setItemExpectStartDate(String itemExpectStartDate) {
            this.itemExpectStartDate = itemExpectStartDate;
        }

        public String getItemExpectEndDate() {
            return itemExpectEndDate;
        }

        public void setItemExpectEndDate(String itemExpectEndDate) {
            this.itemExpectEndDate = itemExpectEndDate;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getExpectEndDate() {
            return expectEndDate;
        }

        public void setExpectEndDate(String expectEndDate) {
            this.expectEndDate = expectEndDate;
        }

        public String getExpectStartDate() {
            return expectStartDate;
        }

        public void setExpectStartDate(String expectStartDate) {
            this.expectStartDate = expectStartDate;
        }


        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public int getCucId() {
            return cucId;
        }

        public void setCucId(int cucId) {
            this.cucId = cucId;
        }

        public String getDecorationOrderNo() {
            return decorationOrderNo;
        }

        public void setDecorationOrderNo(String decorationOrderNo) {
            this.decorationOrderNo = decorationOrderNo;
        }

        public String getHouseAddress() {
            return houseAddress;
        }

        public void setHouseAddress(String houseAddress) {
            this.houseAddress = houseAddress;
        }

        public String getAcceptDate() {
            return acceptDate;
        }

        public void setAcceptDate(String acceptDate) {
            this.acceptDate = acceptDate;
        }

        public String getAcceptProgress() {
            return acceptProgress;
        }

        public void setAcceptProgress(String acceptProgress) {
            this.acceptProgress = acceptProgress;
        }

        public int getSubFinishTime() {
            return subFinishTime;
        }

        public void setSubFinishTime(int subFinishTime) {
            this.subFinishTime = subFinishTime;
        }

        public int getDelayDay() {
            return delayDay;
        }

        public void setDelayDay(int delayDay) {
            this.delayDay = delayDay;
        }

        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public String getFinishDate() {
            return finishDate;
        }

        public void setFinishDate(String finishDate) {
            this.finishDate = finishDate;
        }

        public String getOrderItemNo() {
            return orderItemNo;
        }

        public void setOrderItemNo(String orderItemNo) {
            this.orderItemNo = orderItemNo;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getOrderStatusName() {
            return orderStatusName;
        }

        public void setOrderStatusName(String orderStatusName) {
            this.orderStatusName = orderStatusName;
        }
    }
}
