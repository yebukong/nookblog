package pers.mine.nookblog.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.mine.nookblog.entity.BaseEntity;

import java.util.Date;

/**
 * @author yebukong
 * @description 基础服务接口
 * @since 2018-10-21 12:06
 */
public interface IBaseService<T extends BaseEntity> extends IService<T> {
    /**
     * 更新记录状态位通过Id
     * @param entity 仅设置id,status即可
     * @return
     */
    default public boolean updateStatusById(T entity) {
        T trimEntity =(T)ClassUtils.newInstance(ClassUtils.getUserClass(entity));
        trimEntity.setId(entity.getId());
        trimEntity.setStatus(entity.getStatus());
        trimEntity.setModified(new Date());
        return updateById(trimEntity);
    }

    /**
     * 更新记录流水号通过旧流水号
     * @param oldEntity 仅设置旧id即可
     * @param newEntity 仅设置新id即可
     */
    default public boolean updateIdById(T oldEntity,T newEntity) {
        T trimEntity =(T)ClassUtils.newInstance(ClassUtils.getUserClass(oldEntity));
        trimEntity.setId(newEntity.getId());
        trimEntity.setModified(new Date());
        return update(trimEntity,new UpdateWrapper<T>().lambda().eq(T::getId,oldEntity.getId()));
    }

}
