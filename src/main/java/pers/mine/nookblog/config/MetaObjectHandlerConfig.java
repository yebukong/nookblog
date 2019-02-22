package pers.mine.nookblog.config;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

@Component
@Slf4j
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        log.info("插入自动填充[created,modified]:" + metaObject.toString());
        Object created = getFieldValByName("created", metaObject);
        Object modified = getFieldValByName("modified", metaObject);
        if (created == null) {
            setFieldValByName("created", new Date(), metaObject);
        }
        if (modified == null) {
            setFieldValByName("modified", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新自动填充[modified]:" + metaObject.toString());
        Object modified = getFieldValByName("modified", metaObject);
        if (modified == null) {
            setFieldValByName("modified", new Date(), metaObject);
        }
    }
}
