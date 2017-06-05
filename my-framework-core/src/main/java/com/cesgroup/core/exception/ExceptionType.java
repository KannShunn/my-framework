package com.cesgroup.core.exception;

/**
 * 异常类型枚举
 * 
 * @author v_pinzhao
 * 
 */
public enum ExceptionType {

    /**
     * web layer exception
     */
    EXCEPTION_WEB(0, "error.web.000", "web layer exception"),

    /**
     * service layer exception
     */
    EXCEPTION_SVS(1, "error.svs.000", "service layer exception"),

    /**
     * dal layer exception
     */
    EXCEPTION_DAL(2, "error.dal.000", "dal layer exception"),

    /**
     * integration layer exception
     */
    EXCEPTION_INT(3, "error.int.000", "integration layer exception"),

    /**
     * user define exception
     */
    EXCEPTION_BIZ(4, "error.biz.000", "user define exception"),

    /**
     * defalut exception
     */
    EXCEPTION_DEF(5, "error.def.000", "defalut exception");

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置异常编码
     * 
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 索引
     */
    private int index;
    /**
     * 错误代码
     */
    private String code;
    /**
     * 描述
     */
    private String desc;

    /**
     * 获取异常描述信息
     * 
     * @return 异常描述信息
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置异常描述信息
     * 
     * @param desc 异常描述信息
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 构造方法
     * 
     * @param index 索引
     * @param code 编码
     * @param desc 描述
     */
    ExceptionType(int index, String code, String desc) {
        this.index = index;
        this.code = code;
        this.desc = desc;
    }

    /**
     * 返回索引
     * 
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * 设置索引
     * 
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

}
