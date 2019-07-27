package pers.mine.nookblog.service;

import pers.mine.nookblog.entity.Code;

import java.util.List;

/**
 *
 * Code 表数据服务层接口
 *
 */
public interface ICodeService extends IBaseService<Code> {

    /**
     * 获取指定code下级代码列表
     * @param codeNo
     * @return
     */
    public List<Code> getCodeItems(String codeNo);

    /**
     * 获取指定code下itemNo
     * @param codeNo
     * @param itemNo
     * @return
     */
    public Code getCodeItem(String codeNo,String itemNo);

    /**
     * 校验并插入新标签
     * @return
     */
    public int checkAndSaveArticleTags(String...itemNos);

}