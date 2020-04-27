package com.company.project.service.impl;

import com.company.project.dao.NewsMapper;
import com.company.project.model.News;
import com.company.project.model.NewsVo;
import com.company.project.service.NewsService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/15.
 */
@Service
@Transactional
public class NewsServiceImpl extends AbstractService<News> implements NewsService {
    @Resource
    private NewsMapper newsMapper;

    @Override
    public List<NewsVo> getVoList(List<News> list) {
        List<NewsVo> voList = new ArrayList<>();
        for (News ele :
                list) {
            NewsVo vo = new NewsVo(ele);
            voList.add(vo);
        }
        return voList;
    }
}
