package cn.kgc.smbms.controller.user;

import cn.kgc.smbms.pojo.User;
import cn.kgc.smbms.service.user.UserService;
import cn.kgc.smbms.util.PageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    /*跳转到登录界面*/
    @RequestMapping("/tologin")
    public String toLogin() {
        return "login";
    }

    /*登录成功到主界面*/
    @RequestMapping("/dologin")
    public String doLogin(String userCode, String userPassword, HttpSession session) {
        User user = userService.doLogin(userCode, userPassword);
        if (user != null) {
            session.setAttribute("user", user);
            return "frame";
        } else {
            return "login";
        }
    }

    /*展示用户界面*/
    @RequestMapping("/userlist")
    public String listUser(@RequestParam(value = "userCode", required = false) String userCode,
                           @RequestParam(value = "userRole", required = false) String userRole,
                           @RequestParam(value = "pageIndex", required = false) String pageIndex,
                           Model model) {
        System.out.println(userCode + "," + userRole + "," + pageIndex);
        /*设置每页显示的数据行数*/
        int pageSize = 5;
        /*当前页码*/
        int currentPageNo = 1;
        /*判断userRole是否为空*/
        int _userRole = 0;
        if (userRole != null && !"".equals(userRole)) {
            _userRole = Integer.valueOf(userRole);
        }
        //判断是否需要更新当前页码
        if (pageIndex != null) {
            currentPageNo = Integer.valueOf(pageIndex);
        }

        //查询的总数据记录数
        User user = userService.listUserCount(userCode, _userRole);
        Integer totalCount = user.getCount();
        /*构建PageSupport*/
        PageSupport pageSupport = new PageSupport();
        pageSupport.setPageSize(pageSize);//设置每页显示的行数
        pageSupport.setCurrentPageNo(currentPageNo);//传入当前页码
        pageSupport.setTotalCount(totalCount);//传入总页数
        //计算总页数
        int totalPageCount = pageSupport.getTotalPageCount();
        System.out.println("总数:" + totalCount);
        //控制首页和尾页
        if (currentPageNo < 1) {
            currentPageNo = 1;//回到第一页
        } else if (currentPageNo > totalPageCount) {
            currentPageNo = totalPageCount;//回到最后一页
        }
        List list = new ArrayList();
        list.add(userCode);
        list.add(_userRole);
        list.add(currentPageNo);
        list.add(pageSize);
        List<User> userList = userService.findAllUser(userCode, _userRole, currentPageNo, pageSize);
        /*用户角色集合*/
        /*绑定用户集合数据*/
        model.addAttribute("userList", userList);
        model.addAttribute("totalPageCount", totalPageCount);//总页数
        model.addAttribute("totalCount", totalCount);//总数据
        model.addAttribute("currentPageNo", currentPageNo);//当前页数
        //角色集合

        return "userlist";
    }

    /*展示用户界面*/
  /*  @RequestMapping("/userlist")
    public String listUser(Model model) {
        List<User> userList = userService.findAllUser();
         model.addAttribute("userList",userList);
         return "userlist";
    }

    /*点击退出,销毁session到登录界面*/
    @RequestMapping("/logout")
    public String loginOut(HttpSession session) {
        /*session销毁*/
        session.invalidate();
        /*重定向到请求*/
        // return "redirect:/user/tologin";
        return "login";
    }
}