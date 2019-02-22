package pers.mine.nookblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.mine.nookblog.entity.Code;
import pers.mine.nookblog.entity.User;
import pers.mine.nookblog.mapper.CodeMapper;
import pers.mine.nookblog.service.ICodeService;
import pers.mine.nookblog.utils.StringX;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Code 表数据服务层接口实现类
 */
@Service
public class CodeServiceImpl extends ServiceImpl<CodeMapper, Code> implements ICodeService {
    @Autowired
    private CodeMapper codeMapper;

    public List<Code> getCodeItems(String codeNo) {
        return baseMapper.selectListByRelaNo(codeNo);
    }

    public Code getCodeItem(String codeNo, String itemNo) {
        LambdaQueryWrapper<Code> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Code::getItemNo, itemNo);
        if (StringX.isEmpty(codeNo)) {
            lqw.isNull(Code::getRelaNo);
        } else {
            lqw.eq(Code::getRelaNo, codeNo);
        }
        List<Code> codeItems = super.list(lqw);
        return (codeItems == null||codeItems.isEmpty()) ? null : codeItems.get(0);
    }

    public int checkAndSaveArticleTags(String... itemNos) {
        int result = 0;
        for (String itemNo : itemNos) {
            if(getCodeItem(Code.ARTICLE_TAG,itemNo)==null){
                Code one = new Code();
                one.setItemNo(itemNo);
                one.setItemName(itemNo);
                one.setRelaNo(Code.ARTICLE_TAG);
                one.setStatus("VALID");
                one.setCreated(new Date());
                one.setModified(new Date());
                save(one);
                result++;
            }
        }
        return result;
    }
}