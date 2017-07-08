package com.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/18.
 */
@Data
@Log4j
@NoArgsConstructor//无参构造
@AllArgsConstructor//全参构造
public class UserPojo implements Serializable{
    private String userId;
    private String userName;
    private int age;
    private String password;
    private String oldPassword;
    @Override
    public String toString() {
        return "UserPojo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                '}';
    }
}
