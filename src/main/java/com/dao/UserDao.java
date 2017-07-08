package com.dao;

import com.beans.UserPojo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与数据库交互的类
 */
@Repository  //TODO 持久层注解
public interface UserDao {
    UserPojo queryUser(String userName);
    List<UserPojo> queryAllUser();
    void insertUserBean(UserPojo userPojo);
}
