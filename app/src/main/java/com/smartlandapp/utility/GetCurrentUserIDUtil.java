package com.smartlandapp.utility;

import android.content.Context;

import com.smartlandapp.base.GlobalKey;

public class GetCurrentUserIDUtil {

    public static String currentUserId(Context context) {
        String id = "";
        id = SharedPreferenceUtils.getString(context, GlobalKey.Login.CODE, id);
        return id;
    }
}
