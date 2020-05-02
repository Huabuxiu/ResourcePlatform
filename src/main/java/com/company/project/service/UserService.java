package com.company.project.service;
import com.company.project.model.User;
import com.company.project.core.Service;
import com.company.project.model.UserVo;
import com.company.project.model.UserVoTow;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/31.
 */
public interface UserService extends Service<User> {

    public List<UserVo> getUserVoList(List<User> list);

    public List<UserVo> getExamineVoList(List<User> list);

    public UserVoTow getUserVo(User user);

}
