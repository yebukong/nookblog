package pers.mine.nookblog.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author yebukong
 * @description 文章
 * @since 2018-10-06 10:33
 */
@Data
@ToString(callSuper = true)
public class Article extends BaseEntity{
    private String title;
    private String brief;
    private String content;
    private String type;
    private String tags;
    private Integer wordCount;
    private String ip;
    private String address;
    private Integer commentNum;
    private Integer hits;
}
