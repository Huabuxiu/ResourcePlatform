package com.company.project.service;
import com.company.project.model.News;
import com.company.project.core.Service;
import com.company.project.model.NewsVo;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/15.
 */
public interface NewsService extends Service<News> {
    List<NewsVo> getVoList(List<News> list);

}
