package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.News;
import com.company.project.model.NewsVo;
import com.company.project.service.NewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

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
        if (news == null ){
            return ResultGenerator.genFailResult("公告不存在");
        }
        news.setRegTime(new Date());
        news.setTitle(data.get("text_title"));
        news.setHtml(data.get("Html"));
        newsService.update(news);
        return ResultGenerator.genSuccessResult().setMessage("修改成功");
    }

    @PostMapping("/detail")
    public Result detail(@RequestBody Map<String,Integer> data) {
        News news = newsService.findById(data.get("nid"));
        return ResultGenerator.genSuccessResult(news);
    }

    @PostMapping("/list")
    public Result list(){
        List<News> list = newsService.findAll();
        List<NewsVo> voList = newsService.getVoList(list);
        return ResultGenerator.genSuccessResult(voList);
    }

    @PostMapping("/home_list")
    public Result home_list(){
        Condition condition = new Condition(News.class);
        condition.createCriteria();
        condition.orderBy("regTime").desc();
        List<News> list = newsService.findByCondition(condition);
        if (list.size() > 6){
            list = list.subList(0,6);
        }
        return ResultGenerator.genSuccessResult(list);
    }
}
