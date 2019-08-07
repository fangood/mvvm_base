package com.zj.th.data.remote.user;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.List;


public class LoginUserDTO implements Serializable {

    private int id;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 坐标系用户id
     */
    private int userId;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 权限组信息，用,号分割的
     */
    private String groupInfo;

    /**
     * 主岗位,员工没有主岗位
     */
    private LzbPositionDto position;

    /**
     * 副岗 ,
     */
    private List<LzbPositionDto> subPosition;

    /**
     * 个人权限
     */
    private List<LzbPersonFunctionDto> personFunctionJsonDtoList;

    /**
     * 手机号
     */
    private String telephone;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(String groupInfo) {
        this.groupInfo = groupInfo;
    }

    @Nullable
    public LzbPositionDto getPosition() {
        return position;
    }

    public void setPosition(LzbPositionDto position) {
        this.position = position;
    }

    public List<LzbPositionDto> getSubPosition() {
        return subPosition;
    }

    public void setSubPosition(List<LzbPositionDto> subPosition) {
        this.subPosition = subPosition;
    }

    public List<LzbPersonFunctionDto> getPersonFunctionJsonDtoList() {
        return personFunctionJsonDtoList;
    }

    public void setPersonFunctionJsonDtoList(List<LzbPersonFunctionDto> personFunctionJsonDtoList) {
        this.personFunctionJsonDtoList = personFunctionJsonDtoList;
    }

    public void assertPositionNonNull() throws Exception {
        if (position == null && (subPosition == null || subPosition.size() == 0)) {
            throw new Exception("用户岗位不能为空");
        }
    }
}
