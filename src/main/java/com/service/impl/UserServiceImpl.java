package com.service.impl;

import com.beans.UserPojo;
import com.dao.UserDao;
import com.service.inface.UserServiceInface;
import com.uilts.TextUilt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/6/19.
 * 后台逻辑的实现类
 */
@Service("UserServiceInface")
public class UserServiceImpl implements UserServiceInface {
    @Resource
    public UserDao userDao;

    public boolean toLogin(String name, String password) {
        UserPojo userPojo = userDao.queryUser(name);
        if (userPojo == null) {
            return false;
        } else {
            if (password.equals(userPojo.getPassword())) {
                return true;
            }
            return false;
        }
    }

    public boolean toRegister(UserPojo userPojo) {
        if (userPojo != null) {
            if (!TextUilt.isEmptys(userPojo.getUserName())
                    && !TextUilt.isEmptys(userPojo.getPassword()) &&
                    !TextUilt.isEmptys(userPojo.getOldPassword())) {
                     if(userPojo.getPassword().equals(userPojo.getOldPassword())){
                         userDao.insertUserBean(userPojo);
                         return true;
                     }
            }
        }
        return false;
    }
}
