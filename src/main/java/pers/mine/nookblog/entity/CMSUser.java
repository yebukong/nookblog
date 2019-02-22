package pers.mine.nookblog.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yebukong
 * @description 后台用户
 * @since 2018-10-23 10:02
 */
@Data
@ToString(callSuper = true)
@Component
@ConfigurationProperties(prefix = "mine-info")
public class CMSUser extends BaseEntity{
    public static final String SESSION_NAME = "cms_user_info";

    private String userID;
    private String userName;
    private String password;
}
