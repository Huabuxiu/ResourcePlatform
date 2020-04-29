package com.company.project.util;

import com.alibaba.fastjson.JSON;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.*;

public class QuartzMain {




    public static void main(String[] args){
        String com = "useradd -m adduserByjavacode&&(echo \"testcreate\"; echo \"testcreate\") |passwd adduserByjavacode";
//        String com = "userdel -r adduserByjavacode";
//        String com = "cat /etc/passwd |cut -f 1 -d :";

//

        Boolean result=ConnectLinuxCommand.connectLinux("123.206.255.202","root","Huabuxiu3817",com);
        System.out.println(result);
    }

}
