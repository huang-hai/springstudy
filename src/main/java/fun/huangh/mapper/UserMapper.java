package fun.huangh.mapper;

import fun.huangh.bean.User;

public interface UserMapper {

    void insertUser(User user);

    User getUser(Integer id);
}
