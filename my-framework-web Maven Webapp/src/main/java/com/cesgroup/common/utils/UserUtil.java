package com.cesgroup.common.utils;

import com.cesgroup.shiro.ShiroUser;
import org.apache.shiro.SecurityUtils;

/**
 * 类描述.
 * <p>
 * 描述:一段简短的描述
 * </p>
 * <p>
 * Company:红星美凯龙家居集团股份有限公司
 * </p>
 *
 * @author 管俊(lion_guan@foxmail.com)
 * @version 1.0
 * @date 2019/12/1 14:16
 */
public class UserUtil {


    public static ShiroUser getCurrentUser()
    {
        ShiroUser currentUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return currentUser;
    }
}
