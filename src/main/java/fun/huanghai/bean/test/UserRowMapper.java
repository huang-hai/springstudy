package fun.huanghai.bean.test;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper {


    @Nullable
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return new User(resultSet.getInt("id"),resultSet.getString("name"),
                resultSet.getInt("age"),resultSet.getString("sex"));
    }
}
