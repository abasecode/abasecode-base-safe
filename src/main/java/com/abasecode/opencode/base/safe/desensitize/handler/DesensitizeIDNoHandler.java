package com.abasecode.opencode.base.safe.desensitize.handler;

import com.abasecode.opencode.base.safe.desensitize.DesensitizeIDNo;

import com.abasecode.opencode.base.safe.util.CodeDesensitizeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
@MappedTypes(DesensitizeIDNo.class)
@Slf4j
@Component
public class DesensitizeIDNoHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        try{
            String s1 = CodeDesensitizeUtils.desensitizeCnIdNo(s);
            preparedStatement.setString(i,s1);
            log.info("**********************");
            log.info("原值："+ s);
            log.info("脱敏："+s1);
            log.info("**********************");
        }catch (Exception e){
            preparedStatement.setString(i,s);
        }
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String s1 = resultSet.getString(s);
        return s1;
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
