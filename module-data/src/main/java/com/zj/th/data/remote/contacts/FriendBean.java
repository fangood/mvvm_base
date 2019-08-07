package com.zj.th.data.remote.contacts;

import java.io.Serializable;


public class FriendBean implements Serializable {

    private String friendName;
    private int friendUserId;
    private String telephone;
    private String profession;
    private String avatarSrc;
    private boolean isCanDelegate ;
    private String professionNo ;

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public int getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(int friendUserId) {
        this.friendUserId = friendUserId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAvatarSrc() {
        return avatarSrc;
    }

    public void setAvatarSrc(String avatarSrc) {
        this.avatarSrc = avatarSrc;
    }

    public boolean isCanDelegate() {
        return isCanDelegate;
    }

    public void setCanDelegate(boolean canDelegate) {
        isCanDelegate = canDelegate;
    }

    public String getProfessionNo() {
        return professionNo;
    }

    public void setProfessionNo(String professionNo) {
        this.professionNo = professionNo;
    }
}
