package com.zj.th.base;

/**
 * 用户角色
 * 项目经理、测量、保洁、供应商
 *
 */
public enum Role {

    //1.测量
    //2.施工
    //3.保洁
    Measurer(1), ProjectManager(2), Cleaner(3), @Deprecated Supplier(0);

    private String name;
    private int orderType;

    Role(int orderType) {
        switch (orderType) {
            case 1:
                this.name = "测量";
                break;
            case 2:
                this.name = "施工";
                break;
            case 3:
                this.name = "保洁";
                break;
        }
        this.orderType = orderType;
    }

    public String getName() {
        return name;
    }

    public int getOrderType() {
        return orderType;
    }

    public static Role getByOrderType(int orderType) {
        switch (orderType) {
            case 1:
                return Measurer;
            case 2:
                return ProjectManager;
            case 3:
                return Cleaner;
            default:
                return null;
        }
    }
}
