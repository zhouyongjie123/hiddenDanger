package com.zyj.hiddendanger.database.handler;

import com.baomidou.mybatisplus.core.toolkit.AES;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(String.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class PasswordEncryptTypeHandler extends BaseTypeHandler<String> {
    // 加密密钥
    private static final String SECRET_KEY = "1234567890123456";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        if (StringUtils.hasText(parameter)) {
            // 写入数据库时加密
            ps.setString(i, AES.encrypt(parameter, SECRET_KEY));
        } else {
            ps.setString(i, parameter);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName);
        return decrypt(columnValue);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValue = rs.getString(columnIndex);
        return decrypt(columnValue);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String columnValue = cs.getString(columnIndex);
        return decrypt(columnValue);
    }

    private String decrypt(String encryptedValue) {
        if (StringUtils.hasText(encryptedValue)) {
            try {
                return AES.decrypt(encryptedValue, SECRET_KEY);
            } catch (Exception e) {
                // 如果解密失败，可能是未加密的原始数据，直接返回
                return encryptedValue;
            }
        }
        return encryptedValue;
    }
}
