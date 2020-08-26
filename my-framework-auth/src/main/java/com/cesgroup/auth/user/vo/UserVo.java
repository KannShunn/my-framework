package com.cesgroup.auth.user.vo;

import com.cesgroup.auth.user.entity.User;

/**
 * 类描述.
 * <p>
 * 描述:一段简短的描述
 * </p>
 * <p>
 * Company:lion_guan
 * </p>
 *
 * @author 管俊(lion_guan@foxmail.com)
 * @version 1.0
 * @date 2017/6/5 13:52
 */
public class UserVo extends User {

    private String resUrl;

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }
}
