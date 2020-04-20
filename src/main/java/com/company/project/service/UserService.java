package com.company.project.service;
import com.company.project.model.User;
import com.company.project.core.Service;
import com.company.project.model.UserVo;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/31.
 */
public interface UserService extends Service<User> {

    public List<UserVo> getUserVoList(List<User> list);

}
