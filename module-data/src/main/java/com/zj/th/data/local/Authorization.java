package com.zj.th.data.local;

import android.content.Context;
import android.util.Base64;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 存储用户token
 */
public class Authorization {

    private final String SP_NAME = "sp_authorization";
    private final String TOKEN = "token";

    public static Authorization get() {
        return new Authorization();
    }

    public void setToken(@NonNull Context context, String token) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(TOKEN, token)
                .commit();
    }

    public String getToken(@NonNull Context context) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
                .getString(TOKEN, "");
    }

    public TokenInfo getTokenInfo(@NonNull Context context) {
        String token = getToken(context);
        String[] pices = token.split("\\.");
        if (pices.length == 3) {
            return new Gson().fromJson(new String(Base64.decode(pices[1], Base64.DEFAULT)), TokenInfo.class);
        }
        return new TokenInfo();
    }

    /**
     * token中由.分割开的第二段字符串，用base64解码出的用户信息对象
     */
    public class TokenInfo implements Serializable {

        @SerializedName("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name")
        private String userId;
        private String UserName;
        private String Mobile;
        private String Email;
        private String NickName;
        private String QRUrl;
        private String HeadIconUrl;
        private int nbf;
        private int exp;
        private String iss;
        private String aud;

        public String getUserId() {
            return userId;
        }

        public String getUserName() {
            return UserName;
        }

        public String getMobile() {
            return Mobile;
        }

        public String getEmail() {
            return Email;
        }

        public String getNickName() {
            return NickName;
        }

        public String getQRUrl() {
            return QRUrl;
        }

        public String getHeadIconUrl() {
            return HeadIconUrl;
        }

        public int getNbf() {
            return nbf;
        }

        public int getExp() {
            return exp;
        }

        public String getIss() {
            return iss;
        }

        public String getAud() {
            return aud;
        }
    }

}
