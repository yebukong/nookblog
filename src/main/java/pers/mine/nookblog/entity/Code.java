package pers.mine.nookblog.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yebukong
 * @description 代码表
 * @since 2018-10-06 22:12
 */
@Data
@ToString(callSuper = true)
public class Code extends BaseEntity implements Serializable {
    /**排序临时列[同一code下代码项聚集]*/
    public static final String TEMP_SORT_COLUMN = "CONCAT_WS('-',IF(LENGTH(relaNo),relaNo,itemNo),RPAD(sortNo,10, '0'))";
    /**项目配置信息*/
    public static final String SYS_CONFIG = "SysConfig";
    /**文章标签*/
    public static final String ARTICLE_TAG = "ArticleTag";
    /**基础状态码 */
    public static final String BASE_STATUS = "BaseStatus";
    /**文章类型*/
    public static final String ARTICLE_TYPE = "ArticleType";


    private String itemNo;
    private String itemName;
    private String sortNo;
    private String value;
    private String relaNo;
    private String description;
}
