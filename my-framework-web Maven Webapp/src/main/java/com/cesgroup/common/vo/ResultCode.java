package com.cesgroup.common.vo;

/**
 * @author:杨果
 * @date:16/3/10 上午9:29
 * <p/>
 * Description:
 * <p/>
 * REST接口返回的结果状态码,这些结果状态码参照HTTP协议
 */
public enum ResultCode {
    /**
     * Success
     */
    C200(200, "操作成功"),
    /**
     * Forbidden"
     */
    C401(401, "未授权"),
    /**
     * Forbidden"
     */
    C403(403, "访问受限"),
    /**
     * Not Found"
     */
    C404(404, "资源未找到"),
    /**
     * Parameters Error"
     */
    C400(400, "参数列表错误"),
    /**
     * Internal Server Error"
     */
    C500(500, "系统内部错误"),

    C301(301, "资源已被移除"),

    C303(303, "重定向"),

    C501(501, "接口未实现"),

    C429(429, "请求过多被限制"),

    C9999(9999,"业务异常"),

    C415(415, "不支持的数据（媒体类型）");

    private int code;//code
    private String desc;//description

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ResultCode format(String name) {
        for (ResultCode value : ResultCode.values()) {
            if (name.equals(value.toString())) {
                return value;
            }
        }
        return null;

    }

    public static ResultCode formatName(int valKey) {
        for (ResultCode value : ResultCode.values()) {
            if (valKey == value.getCode()) {
                return value;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
