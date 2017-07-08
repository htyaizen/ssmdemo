package com.service.inface;

/**
 * Created by Administrator on 2017/6/19.
 */

import com.beans.UserPojo;

/**
 * 返回数据给客户端的接口
 */
public interface UserServiceInface {
    boolean toLogin(String name,String password);

    boolean toRegister(UserPojo userPojo);
}
