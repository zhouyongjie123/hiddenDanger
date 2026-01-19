package com.zyj.hiddendanger.database;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zyj.hiddendanger.core.id.IdGenerator;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

public class BasePojoInjector implements MetaObjectHandler {
    private final IdGenerator<String> idGenerator;

    public BasePojoInjector(IdGenerator<String> idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        Object originalObject = metaObject.getOriginalObject();
        if (originalObject instanceof SuperEntity superEntity) {
            if (superEntity.getDeleted() == null) superEntity.setDeleted(0);
            if (superEntity.getCreateTime() == null) superEntity.setCreateTime(new Date());
            if (superEntity.getUpdateTime() == null) superEntity.setUpdateTime(new Date());
            if (superEntity.getId() == null) superEntity.setId(idGenerator.generate());
        }
        if (originalObject instanceof Entity entity) {
            if (entity.getCreatorId() == null) {
                Long loginId = StpUtil.getLoginIdAsLong();
                entity.setCreatorId(loginId);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object originalObject = metaObject.getOriginalObject();
        if (originalObject instanceof SuperEntity superEntity) {
            if (superEntity.getUpdateTime() == null) superEntity.setUpdateTime(new Date());
        }

        if (originalObject instanceof Entity entity) {
            if (entity.getUpdaterId() == null) {
                long loginId = StpUtil.getLoginIdAsLong();
                ((Entity) originalObject).setUpdaterId(loginId);
            }
        }
    }
}
