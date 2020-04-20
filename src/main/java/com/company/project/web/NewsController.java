package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.News;
import com.company.project.service.NewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2020/03/15.
*/
@RestController
@RequestMapping("/news")
public class NewsController {
    @Resource
    private NewsService newsService;

    @PostMapping("/add")
    public Result add(@RequestBody Map<String,String> data) {
        News news = new News();
        news.setHtml(data.get("Html"));
        news.setTitle(data.get("text_title"));
        news.setRegTime(new Date());
        newsService.save(news);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Map<String,Integer> data) {
        newsService.deleteById(data.get("nid"));
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Map<String,String> data) {
        News news = newsService.findById(Integer.parseInt(data.get("nid")));
        news.setRegTime(new Date());
        news.setTitle(data.get("text_title"));
        news.setHtml(data.get("Html"));
        newsService.update(news);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestBody Map<String,Integer> data) {
        News news = newsService.findById(data.get("nid"));
        return ResultGenerator.genSuccessResult(news);
    }

    @PostMapping("/list")
    public Result list(){
        List<News> list = newsService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }
}
