package fun.huanghai.bean.service;

import fun.huanghai.bean.test.User;
import fun.huanghai.bean.test.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

public class UserServiceImpl implements UserService{

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    @Override
    public void save(User user) throws RuntimeException{
        jdbcTemplate.update("insert into user(name,age,sex)values(?,?,?)",
                new Object[]{user.getName(),user.getAge(),user.getSex()},
                new int[]{Types.VARCHAR,Types.INTEGER,Types.VARCHAR});
        throw new RuntimeException("aa");
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("select * from user",new UserRowMapper());
    }
}
