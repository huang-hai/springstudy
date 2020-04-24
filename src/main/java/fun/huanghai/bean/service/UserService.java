package fun.huanghai.bean.service;

import fun.huanghai.bean.test.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<User> getUsers();
}
