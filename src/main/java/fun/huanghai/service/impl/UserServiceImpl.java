package fun.huanghai.service.impl;

import fun.huanghai.service.UserService;

public class UserServiceImpl implements UserService{
    /**
     * 目标方法
     */
    @Override
    public void add(String name) {
        System.out.println(name);
        System.out.println("--------------------add()--------------------");
    }
}
