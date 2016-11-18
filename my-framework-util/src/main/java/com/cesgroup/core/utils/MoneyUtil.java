package com.cesgroup.core.utils;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/8/29.
 */
public class MoneyUtil {

    // ￥1,234
    public static String toRMB(int money) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return "￥"+decimalFormat.format(money);
    }

    //加粗￥1,234
    public static String toBoldRMB(int money) {
        return "<span style='font-weight:bold'>" + toRMB(money)+"</span>";
    }

    public static String toBold(String text) {
        return "<span style='font-weight:bold'>" + text+"</span>";
    }
}

