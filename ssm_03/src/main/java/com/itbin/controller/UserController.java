package com.itbin.controller;

import com.itbin.domain.Condition;
import com.itbin.domain.PageBean;
import com.itbin.domain.ResultInfo;
import com.itbin.domain.User;
import com.itbin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private String[] allowPic = {"image/jpeg","image/png"};

//登录
    @RequestMapping("/login")
    public String login(User user , HttpSession session, Integer ck , HttpServletResponse response,Model model)
    throws  Exception{

        User u = userService.login(user);
        System.out.println(u);
        ResultInfo info = new ResultInfo();
        if(u==null){
            info.setErrorMsg("登录失败");
            model.addAttribute(info);
            return "login";
        }
//        登录成功
        Cookie usernameCookie = new Cookie("username",user.getUsername());
        Cookie pswCookie = new Cookie("password",user.getPassword());
        if(ck!=null&&ck==1){
            usernameCookie.setMaxAge(7*24*60*60);
            pswCookie.setMaxAge(7*24*60*60);
        }else {
            usernameCookie.setMaxAge(0);
            pswCookie.setMaxAge(0);
        }
//        设置路径
        usernameCookie.setPath("/");
        pswCookie.setPath("/");
        response.addCookie(usernameCookie);
        response.addCookie(pswCookie);
        model.addAttribute("user",u);
        session.setAttribute("user",u);
        return "index1";

    }
//分页查询
    @RequestMapping("/findByPage")
    public  String findByPage(@RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
                              @RequestParam(value = "rows",required = false,defaultValue = "5")Integer rows,Model model){
        PageBean<User> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        PageBean<User> pageBean = userService.findByPage(pb);
        model.addAttribute("pb",pageBean);
        return  "list";


    }
//  条件查询
    @RequestMapping("/findUserByPageAndCondition")
    public  String findUserByPageAndCondition(@RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage,
                                              @RequestParam(value = "rows",required = false,defaultValue = "5")Integer rows, Model model, Condition condition){
        PageBean<User> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        PageBean<User> pageBean = userService.findUserByPageAndCondition(pb,condition);
        model.addAttribute("pb",pageBean);
        model.addAttribute("condition",condition);
        return  "list";
    }
//  添加用户
    @RequestMapping("/saveUser")
    public  String  saveUser (User user, MultipartFile picName, HttpServletRequest request, Model model){
      if(!Arrays.asList(allowPic).contains(picName.getContentType())){
          throw  new RuntimeException("您上传的格式有误");
      }
        String path = request.getSession().getServletContext().getRealPath("/pics/");
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        String fileName = picName.getOriginalFilename();
        try {
            picName.transferTo(new File(path,fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setPic(fileName);
        userService.saveUser(user);
        return "redirect:/user/findByPage";

    }
//删除用户
    @RequestMapping("/deleteUser")
    public  String deleteUser(String id){
        userService.deleteUser(id);
        return "redirect:/user/findByPage";
    }
//    删除选中
    @RequestMapping("/delSelect")
    public  String delSelect(String[] id){
        userService.delSelect(id);
        return "redirect:/user/findByPage";
    }
//点击修改
    @RequestMapping("/goUpdate")
    public  String update(Integer id,Model model){
       User user= userService.findById(id);
       model.addAttribute("user",user);
       return "update";
    }
    //修改用户
    @RequestMapping("/update")
    public  String updateUser(User user ){
       userService.update(user);
       return "redirect:/user/findByPage";

    }

}


