package com.blog.controller.home;

import com.blog.entity.User;
import com.blog.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserLoginController {

    @Autowired
    private UserService userService;

    /**
     * 进入用户的登陆/注册界面
     *
     */
    @RequestMapping("/userlogin")
    public String userLogin(){
        return "Home/userlogin";
    }

    /**
     * 进入用户注册页面
     */
    @RequestMapping("/toPageRegisterUser")
    public String toPageRegisterUser(){
        return "Home/registeruser";
    }

    /**
     *
     * 注册用户，将用户信息添加进数据库中
     */
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @ResponseBody
    public String registerUser(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> map = new HashMap<>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        User user = new User();
        user.setUserName(username);
        user.setUserNickname(nickname);
        user.setUserPass(password);
        user.setUserRole("user");
        user.setUserEmail(email);

        int i = userService.registUser(user);
        if(i>0){
            User newUser = userService.getUserByName(user.getUserName());
        }

        return new JSONObject(user).toString();
    }

    /**
     * 普通用户登录
     */
    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    @ResponseBody
    public String loginUser(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");
        User user = userService.getUserByNameOrEmail2(username);
        if (user == null){
            map.put("code",0);
            map.put("msg","用户名无效ssss！");
        }else if (!user.getUserPass().equals(password)){
            map.put("code",0);
            map.put("msg","密码错误！");
        }else {
             //賬戶和密碼正確
            map.put("code",1);
            map.put("msg","success");
            //添加session
            request.getSession().setAttribute("user",user);
            //添加cookie
            if(rememberme!=null){
                //創建cookie
                Cookie nameCookie = new Cookie("username", username);
                //設置有效期是三天
                nameCookie.setMaxAge(60 * 60*24*3);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
               /* response.addCookie(nameCookie);
                response.addCookie(pwdCookie);*/
            }
            user.setUserLastLoginTime(new Date());
            userService.updateUser(user);
        }
        String resule = new JSONObject(map).toString();
        return resule;
    }


    /**
     * 前台用户退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/user/logout")
    public String logout(HttpSession session)  {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/";
    }

}
