package com.zj.th.data.remote.response;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务器返回的常见error code
 */
public class ErrorCode {

    protected static Map<String, String> mErrorCodeMap = new HashMap<>(36);

    static {
        mErrorCodeMap.put("1000", "发生网络错误");
        mErrorCodeMap.put("1001", "网络请求失败");
        mErrorCodeMap.put("1002", "网络请求超时");
        mErrorCodeMap.put("2000", "没有打开相机的权限");
        mErrorCodeMap.put("2001", "没有写存储空间的权限");
        mErrorCodeMap.put("2002", "没有联网权限");
        mErrorCodeMap.put("2003", "没有运动传感器权限");
        mErrorCodeMap.put("2004", "没有录音权限");
        mErrorCodeMap.put("2005", "缺少手机权限（不确定的权限比较笼统）");
        mErrorCodeMap.put("3000", "bizNO和merchantID不匹配");
        mErrorCodeMap.put("3001", "传入的bizNO 有误");
        mErrorCodeMap.put("3002", "此APP的bundle_id在系统的黑名单库里");
        mErrorCodeMap.put("3003", "身份证号和姓名的格式不正确");
        mErrorCodeMap.put("3004", "人脸采集算法初始化失败");
        mErrorCodeMap.put("3005", "身份证号和姓名在一天内使用次数过多");
        mErrorCodeMap.put("3006", "用户当前的设备时间与授权时间不符");
        mErrorCodeMap.put("3007", "用户人脸对比相似度较低");
        mErrorCodeMap.put("3008", "阅读文字存在敏感词");
        mErrorCodeMap.put("3009", "无法获取视频中人像图片");
        mErrorCodeMap.put("3010", "无法获取身份证中的人像图片");
        mErrorCodeMap.put("3011", "文件压缩失败");
        mErrorCodeMap.put("3012", "文件上传失败");
        mErrorCodeMap.put("3013", "文件下载失败");
        mErrorCodeMap.put("3014", "数据解析失败");
        mErrorCodeMap.put("3015", "打开相机失败（部分手机有权限第一次打开失败）");
        mErrorCodeMap.put("3016", "内存问题 ");
        mErrorCodeMap.put("3017", "SDK版本过旧");
        mErrorCodeMap.put("3018", "系统版本过低");
        mErrorCodeMap.put("3019", "数据源错误");
        mErrorCodeMap.put("4000", "服务异常错误");
        mErrorCodeMap.put("4001", "服务逻辑错误");
        mErrorCodeMap.put("4002", "数据重复提交");
        mErrorCodeMap.put("4003", "缺少请求参数");
        mErrorCodeMap.put("5000", "系统服务异常");
        mErrorCodeMap.put("5001", "第三方服务超时异常");
        mErrorCodeMap.put("5002", "第三方服务解析异常");
    }

    public static String getErrorMsg(String code) {
        return mErrorCodeMap.get(code);
    }

}
