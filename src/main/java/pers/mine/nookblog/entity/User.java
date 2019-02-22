package pers.mine.nookblog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import pers.mine.nookblog.entity.enums.AgeEnum;
import pers.mine.nookblog.entity.enums.PhoneEnum;

import java.util.Date;

/**
 * @author yebukong
 * @description 测试用户类
 * @since 2018-09-13 0:17
 */
@Data
@SuppressWarnings("serial")
public class User extends SuperEntity<User> {
    /**
     * 名称
     */
    private String name;
    /**
     * 年龄
     */
    private AgeEnum age;
    /**
     * 这里故意演示注解可无
     */
    @TableField("test_type")
    @TableLogic
    private Integer testType;

    /**
     * 测试插入填充
     */
    @TableField(fill = FieldFill.INSERT)
    private Date testDate;

    private Long role;
    private PhoneEnum phone;

    public User(Long id, String name, AgeEnum age, Integer testType) {
        this.setId(id);
        this.name = name;
        this.age = age;
        this.testType = testType;
    }

    public User(String name, AgeEnum age, Integer testType) {
        this.name = name;
        this.age = age;
        this.testType = testType;
    }
}
