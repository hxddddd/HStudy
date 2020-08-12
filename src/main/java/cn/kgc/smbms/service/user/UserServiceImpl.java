package cn.kgc.smbms.service.user;

import cn.kgc.smbms.dao.user.UserMapper;
import cn.kgc.smbms.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    @Resource
    private UserMapper userDao;

    public User doLogin(String userCode, String userPassword) {
        return userDao.doLogin(userCode, userPassword);
    }

    @Override
    public User listUserCount(String userCode, int userRole) {
        return userDao.listUserCount(userCode, userRole);
    }

/*    @Override
    public int listUserCount(String userCode, int userRole) {
        return userDao.listUserCount(userCode,userRole);
    }*/

    @Override
    public List<User> findAllUser(String userCode, int userRole, int currentPageNo, int pageSize) {
        return userDao.findAllUser(userCode, userRole, currentPageNo, pageSize);
    }
}
