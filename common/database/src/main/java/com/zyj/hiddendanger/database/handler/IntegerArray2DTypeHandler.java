package com.zyj.hiddendanger.database.handler;

import com.alibaba.fastjson2.JSON;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 声明处理的 Java 类型
@MappedTypes(Integer[][].class)
// 声明数据库类型
@MappedJdbcTypes(JdbcType.VARCHAR)
public class IntegerArray2DTypeHandler extends BaseTypeHandler<Integer[][]> {

    /**
     * 存入数据库时：Integer[][] -> JSON字符串
     */
    @Override
    public void setNonNullParameter(
            PreparedStatement ps, int i, Integer[][] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    /**
     * 从数据库读取时：JSON字符串 -> Integer[][]
     */
    @Override
    public Integer[][] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        return JSON.parseObject(json, Integer[][].class);
    }

    @Override
    public Integer[][] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        return JSON.parseObject(json, Integer[][].class);
    }

    @Override
    public Integer[][] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        return JSON.parseObject(json, Integer[][].class);
    }
}
