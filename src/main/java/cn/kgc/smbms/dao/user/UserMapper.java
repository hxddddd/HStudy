package cn.kgc.smbms.dao.user;

import cn.kgc.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User doLogin(@Param("userCode") String userCode, @Param("userPassword") String userPassword);

    List<User> findAllUser(@Param("userCode") String userCode, @Param("userRole") int userRole, @Param("currentPageNo") int currentPageNo, @Param("pageSize") int pageSize);


    User listUserCount(@Param("userCode") String userCode, @Param("userRole") int userRole);
}
