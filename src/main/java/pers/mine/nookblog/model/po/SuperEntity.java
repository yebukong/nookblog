package pers.mine.nookblog.model.po;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Base实体父类
 */
public class SuperEntity<T extends Model<?>> extends Model<T> {
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 状态位
     */
    private String status;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    protected Serializable pkVal() {
        return this.id;
    }

    public String toString() {
        return "BaseEntity(" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", created=" + StrUtil.toString(created) +
                ", modified=" + StrUtil.toString(modified) +
                ')';
    }
}
