package cn.davickk.rdi.essentials.general.dao;

import net.minecraft.util.ResourceLocation;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.StringTypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DimsTypeHandler extends BaseTypeHandler<ResourceLocation> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ResourceLocation parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter.toString());
    }

    @Override
    public ResourceLocation getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return new ResourceLocation(rs.getString(columnName));
    }

    @Override
    public ResourceLocation getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new ResourceLocation(rs.getString(columnIndex));
    }

    @Override
    public ResourceLocation getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new ResourceLocation(cs.getString(columnIndex));
    }
}
