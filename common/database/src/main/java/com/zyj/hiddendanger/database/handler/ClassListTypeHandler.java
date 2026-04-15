package com.zyj.hiddendanger.database.handler;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.MappedTypes;

import java.util.List;

// 强制绑定 List<Class<?>>
@MappedTypes(List.class)
public class ClassListTypeHandler extends AbstractJsonTypeHandler<List<Class<?>>> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final TypeReference<List<Class<?>>> TYPE_REF = new TypeReference<>() {
    };

    @Override
    protected List<Class<?>> parse(String json) {
        try {
            return OBJECT_MAPPER.readValue(json, TYPE_REF);
        } catch (Exception e) {
            throw new RuntimeException("Class列表反序列化失败", e);
        }
    }

    @Override
    protected String toJson(List<Class<?>> obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Class列表序列化失败", e);
        }
    }
}