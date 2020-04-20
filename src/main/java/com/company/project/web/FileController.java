package com.company.project.web;


import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
* Created by Huabuxiu on 2020/01/17.
 *
*/
@RestController
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(path = {"/fileUpload"})
    public Result upload(@RequestParam("file") MultipartFile file) {

        log.info("fileUpload :"+file.getOriginalFilename());
        if (FileUtils.upload(file,file.getOriginalFilename())){
            //上传成功
            return ResultGenerator.genSuccessResult("http://p2959a9495.goho.co/image/"+file.getOriginalFilename());
        }else {
            return ResultGenerator.genFailResult("上传失败");
        }
    }


}
