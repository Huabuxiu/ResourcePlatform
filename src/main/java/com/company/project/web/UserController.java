package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.DepartmentUser;
import com.company.project.model.HostHolder;
import com.company.project.model.MailVo;
import com.company.project.model.User;
import com.company.project.service.DepartmentService;
import com.company.project.service.DepartmentUserService;
import com.company.project.service.UserService;
import com.company.project.service.impl.MailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.*;

/**
* Created by CodeGenerator on 2020/03/31.
*/
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private DepartmentUserService departmentUserService;

    private String PROJECTADDRESS = "http://p2959a9495.goho.co/";

    @Resource
    MailService mailService;

    @Autowired
    HostHolder hostHolder;


    @PostMapping("/login")
    public Result login(@RequestBody Map<String,String> data) {

        User user = userService.findBy("username",data.get("username"));
        if (user == null){
            return ResultGenerator.genFailResult("用户名不存在");
        }
        if(!user.getPassword().equals(data.get("password"))){
            return ResultGenerator.genFailResult("密码错误");
        }
        if (user.getState()==0){
            return ResultGenerator.genFailResult("用户未审核，请等待");
        }
        Map returnMap = new HashMap<String,String>();
        returnMap.put("token",user.getToken());
        return ResultGenerator.genSuccessResult(returnMap);
    }


    @PostMapping("/logout")
    public Result logout() {
        User user = hostHolder.getUser();
        if (user == null){
            return ResultGenerator.genFailResult("用户名不存在");
        }
        return ResultGenerator.genSuccessResult().setMessage("logout");
    }

    @PostMapping("/logon")
    public Result add(@RequestBody Map<String,String> data) {
        User user = new User();
        user.setUsername(data.get("e_mail"));
        user.setPassword(data.get("password"));
        if (userService.findBy("username",data.get("e_mail")) != null){
            return ResultGenerator.genFailResult("用户已存在");
        }
        user.setUserRole(1);
        String secret = "HAHA";//密钥，自己修改
        String token = DigestUtils.md5Hex(UUID.randomUUID() + secret);//混合密钥md
        user.setToken(token);
        user.seteMail(data.get("e_mail"));
        user.setRegTime(new Date());
        user.setName(data.get("name"));
        user.setPhone(data.get("phone"));
        user.setState(0);   //不可用
        userService.save(user);
        DepartmentUser departmentUser = new DepartmentUser();
        departmentUser.setDid( departmentService.findBy("name",data.get("department")).getDid());
        departmentUser.setId(userService.findBy("username",data.get("e_mail")).getId());
        departmentUserService.save(departmentUser);
        User admin  = userService.findBy("userRole",2);
        MailVo mailVo = new MailVo();
        mailVo.setTo(admin.geteMail());
        mailVo.setSubject("用户"+user.getName()+"审核");
        mailVo.setText("有新用户注册，请管理员前往"+PROJECTADDRESS+"user/examine_list 进行审核");
        mailService.sendMail(mailVo);
        return ResultGenerator.genSuccessResult().setMessage("注册成功");
    }



    @PostMapping("/examine")
    public Result examine(@RequestBody Map<String,Integer> data) {
       User user = userService.findById(data.get("id"));
       user.setState(1);
       userService.update(user);
        return ResultGenerator.genSuccessResult().setMessage("用户已审核");
    }

    @PostMapping("/examine_list")
    public Result examine_list() {
        Condition condition = new Condition(User.class);
        condition.createCriteria().andEqualTo("state",0);
        List<User> list = userService.findByCondition(condition);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        List<User> list = userService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }
}
