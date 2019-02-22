package pers.mine.nookblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.mine.nookblog.entity.Article;
import pers.mine.nookblog.mapper.ArticleMapper;
import pers.mine.nookblog.service.IArticleService;

/**
 *
 * Article 表数据服务层接口实现类
 *
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}