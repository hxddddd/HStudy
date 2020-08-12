package cn.kgc.smbms.service.user;

import cn.kgc.smbms.pojo.User;

import java.util.List;

public interface UserService {
    /*登录*/
    User doLogin(String userCode, String userPassword);

    /*查询总数据记录数*/
    User listUserCount(String userCode, int userRole);

    List<User> findAllUser(String userCode, int userRole, int currentPageNo, int pageSize);
}
