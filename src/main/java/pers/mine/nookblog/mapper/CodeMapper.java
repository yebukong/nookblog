package pers.mine.nookblog.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import pers.mine.nookblog.model.po.Code;

import java.util.List;

/**
 * Code 表数据库控制层接口
 */
@Repository
public interface CodeMapper extends SuperMapper<Code> {
    @Select("SELECT * FROM Code WHERE RELANO= #{0} ORDER BY SORTNO ")
    List<Code> selectListByRelaNo(String relaNo);
}