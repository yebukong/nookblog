package pers.mine.nookblog.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.mine.nookblog.SuperMapper;
import pers.mine.nookblog.entity.Article;
import pers.mine.nookblog.entity.User;

import java.util.List;

/**
 * Article 表数据库控制层接口
 */
public interface ArticleMapper extends SuperMapper<Article> {
}