package com.company.project.web;


import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.MailVo;
import com.company.project.service.impl.MailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Resource
    MailService mailService;


    @PostMapping("/send")
    public Result sendMail(@RequestBody Map<String,String> data) {
        MailVo mailVo = new MailVo();
        mailVo.setTo(data.get("to"));
        mailVo.setFrom("1347638343@163.com");
        mailVo.setSubject(data.get("subject"));
        mailVo.setText(data.get("text"));
        mailService.sendMail(mailVo);
        return ResultGenerator.genSuccessResult("邮件发送成功"); //发送邮件和附件
    }

}
